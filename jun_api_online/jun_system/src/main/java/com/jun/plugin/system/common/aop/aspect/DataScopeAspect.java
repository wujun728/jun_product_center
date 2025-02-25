package com.jun.plugin.system.common.aop.aspect;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.constant.Constant;
import com.jun.plugin.common.entity.BaseEntity;
import com.jun.plugin.common.entity.BaseFlowEntity;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.service.HttpSessionService;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.system.entity.*;
import com.jun.plugin.system.service.*;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 概念：最终控制列表中显示哪些 部门/人 创建的数据
 * 1、需要数据权限控制的列表， 需要有创建人字段， 示例：文章管理
 * 2、配置角色的数据范围（本部门，其他部门等）， 以及绑定的部门
 * 3、加个注解，用来查询当前等路人的多个角色（并集）， 根据角色数据范围， 获取绑定的部门id， 查关联的用户id
 * 4、在查某个模块的list或page的时候，手动queryWrapper.in(createId, 关联的用户id)
 *
 * @author wujun
 */
@Aspect
@Component
@Slf4j
public class DataScopeAspect {

    @Resource
    HttpSessionService sessionService;
    @Resource
    RoleService roleService;
    @Resource
    SysRoleDeptService sysRoleDeptService;
    @Resource
    DeptService deptService;
    @Resource
    UserService userService;
    @Resource
    private RedisService redisService;


    @Pointcut("@annotation(com.jun.plugin.common.aop.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
    	long time1= System.currentTimeMillis();
        //获取当前登陆人
        String id = sessionService.getCurrentUserId();
        List<String> userIds = null;
        long time2= System.currentTimeMillis()-time1;
        System.err.println("time cost 1.1 = "+(time2));
//        if(redisService.exists("DataScopeAspect#handleDataScope#"+id)) {
//        	userIds = JSON.parseArray(redisService.get("DataScopeAspect#handleDataScope#"+id), String.class) ;
//        }else {
//        	time2= System.currentTimeMillis()-time1;
//            System.err.println("time cost 1.2 = "+(time2));
        	//获取当前登陆人角色, 如果无角色, 那么不限制
        	List<SysRole> sysRoles = roleService.getRoleInfoByUserId(id);
        	if (CollectionUtils.isEmpty(sysRoles) || sysRoles.size() == 0) {
        		return;
        	}
        	time2= System.currentTimeMillis()-time1;
            System.err.println("time cost 1.3 = "+(time2));
        	//角色未配置数据权限范围, 那么不限制
        	List<SysRole> list = sysRoles.parallelStream().filter(one -> null != one.getDataScope()).collect(Collectors.toList());
        	if (CollectionUtils.isEmpty(list) || list.size() == 0) {
        		return;
        	}
        	//如果存在某角色配置了全部范围， 那么不限制
        	if (list.stream().anyMatch(sysRole -> Constant.DATA_SCOPE_ALL.equals(sysRole.getDataScope()))) {
        		return;
        	}
        	//获取绑定的人
        	userIds = this.getUserIdsByRoles(list, id);
        	System.err.println("datascope userIds = "+userIds);
//        	redisService.set("DataScopeAspect#handleDataScope#"+id, JSON.toJSONString(userIds));
//        }
        
        Object params = joinPoint.getArgs()[0];
        if (params instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) params;
            baseEntity.setCreateIds(userIds);
        }else if (params instanceof BaseFlowEntity) {
        	BaseFlowEntity baseEntity = (BaseFlowEntity) params;
        	baseEntity.setCreateIds(userIds);
        }

    }

    /**
     * 获取最终的用户id
     *
     * @param sysRoles 角色
     * @param userId   当前用户id
     * @return 用户id集合
     */
    private List<String> getUserIdsByRoles(List<SysRole> sysRoles, String userId) {
        //本人
        SysUser sysUser = userService.getById(userId);
        log.info("userId="+userId);
        //本部门
        SysDept sysDept = deptService.getById(sysUser.getDeptId());
        log.info("sysDept="+JSON.toJSONString(sysDept));
        //部门ids， 定义哪些部门最终拥有权限查看
        LinkedList<Object> deptList = new LinkedList<>();
        //用户ids，定义列表中哪些人创建的可查看
        LinkedList<Object> userIdList = new LinkedList<>();
        //根据数据权限范围分组， 不同的数据范围不同的逻辑处理
        Map<Integer, List<SysRole>> dataScopeMap = sysRoles.parallelStream().collect(Collectors.groupingBy(SysRole::getDataScope));
        dataScopeMap.forEach((k, v) -> {
            if (Constant.DATA_SCOPE_CUSTOM.equals(k)) {
                //自定义
                //根据角色id，获取所有自定义关联的部门id
                QueryWrapper<SysRoleDeptEntity> queryWrapper = Wrappers.<SysRoleDeptEntity>query().select("dept_id").in("role_id", v.parallelStream().map(SysRole::getId).collect(Collectors.toList()));
                deptList.addAll(sysRoleDeptService.listObjs(queryWrapper));
            } else if (Constant.DATA_SCOPE_DEPT_AND_CHILD.equals(k)) {
                //本部门及以下
                if (sysDept != null && StringUtils.isNotBlank(sysDept.getDeptNo())) {
                    //获取本部门以下所有关联的部门
                    QueryWrapper<SysDept> queryWrapper = Wrappers.<SysDept>query().select("id").like("relation_code", sysDept.getDeptNo());
                    deptList.addAll(deptService.listObjs(queryWrapper));
                }
            } else if (Constant.DATA_SCOPE_DEPT.equals(k)) {
                //本部门
                if (sysDept != null && StringUtils.isNotBlank(sysDept.getId())) {
                    deptList.add(sysDept.getId());
                }
            } else if (Constant.DATA_SCOPE_DEPT_SELF.equals(k)) {
                //自己
                userIdList.add(userId);
            }
        });
        log.info("deptList="+JSON.toJSONString(deptList));
        if (!CollectionUtils.isEmpty(deptList)) {
            QueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>query().select("id").in("dept_id", deptList);
            userIdList.addAll(userService.listObjs(queryWrapper));
        }
        log.info("userIdList="+JSON.toJSONString(userIdList));
        //如果配置了角色数据范围， 最终没有查到userId， 那么返回无数据
        if (CollectionUtils.isEmpty(userIdList)) {
            throw new BusinessException("无数据");
        }
        return userIdList.parallelStream().map(Object::toString).collect(Collectors.toList());
    }
}
