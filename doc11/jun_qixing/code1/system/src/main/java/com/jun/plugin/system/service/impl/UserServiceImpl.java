package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.util.JwtUtil;
import com.jun.plugin.common.utils.PasswordUtils;
import com.jun.plugin.module.utils.JUNUtil;
import com.jun.plugin.system.entity.SysDept;
import com.jun.plugin.system.entity.SysRole;
import com.jun.plugin.system.entity.SysUser;
import com.jun.plugin.system.mapper.SysDeptMapper;
import com.jun.plugin.system.mapper.SysUserMapper;
import com.jun.plugin.system.service.*;
import com.jun.plugin.system.vo.req.UserRoleOperationReqVO;
import com.jun.plugin.system.vo.resp.LoginRespVO;
import com.jun.plugin.system.vo.resp.UserOwnRoleRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private HttpSessionService httpSessionService;

    @Value("${spring.redis.allowMultipleLogin}")
    private Boolean allowMultipleLogin;
    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public void register(SysUser sysUser) {
        SysUser sysUserOne = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, sysUser.getUsername()));
        if (sysUserOne != null) {
            throw new BusinessException("用户名已存在！");
        }
        sysUser.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(sysUser.getPassword(), sysUser.getSalt());
        sysUser.setPassword(encode);
        sysUserMapper.insert(sysUser);
    }

    @Override
    public LoginRespVO login(SysUser vo) {
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, vo.getUsername()));
        if (null == sysUser) {
            throw new BusinessException(BaseResponseCode.NOT_ACCOUNT);
        }
        if (sysUser.getStatus() == 2) {
            throw new BusinessException(BaseResponseCode.USER_LOCK);
        }
        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getPassword(), sysUser.getPassword())) {
            throw new BusinessException(BaseResponseCode.PASSWORD_ERROR);
        }
        LoginRespVO respVO = new LoginRespVO();
        BeanUtils.copyProperties(sysUser, respVO);

        //是否删除之前token， 此处控制是否支持多登陆端；
        // true:允许多处登陆; false:只能单处登陆，顶掉之前登陆
        if (!allowMultipleLogin) {
            httpSessionService.abortUserById(sysUser.getId());
        }
        if (StringUtils.isNotBlank(sysUser.getDeptId())) {
            SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
            if (sysDept != null) {
                sysUser.setDeptNo(sysDept.getDeptNo());
                sysUser.setDeptName(sysDept.getName());
                respVO.setDeptNO(sysDept.getDeptNo());
                respVO.setDeptID(sysUser.getDeptId());
                respVO.setDeptName(sysDept.getName());
            }
        }
        List<String> roles = roleService.getRoleNames(sysUser.getId());
        respVO.setIsAdmin("0");
        if(roles.contains("管理员")||roles.contains("超级管理员")) {
        	respVO.setIsAdmin("1");
        }
        String token = JwtUtil.sign(sysUser.getUsername());
        String accesstoken = httpSessionService.createTokenAndUser(sysUser,roles , permissionService.getPermissionsByUserId(sysUser.getId()),token);
        respVO.setAccessToken(accesstoken);
        respVO.setAuthorization(token);
        return respVO;
    }

    @Override
    public void updateUserInfo(SysUser vo) {


        SysUser sysUser = sysUserMapper.selectById(vo.getId());
        if (null == sysUser) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }

        //如果用户名变更
        if (!sysUser.getUsername().equals(vo.getUsername())) {
            SysUser sysUserOne = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, vo.getUsername()));
            if (sysUserOne != null) {
                throw new BusinessException("用户名已存在！");
            }
        }

        //如果用户名、密码、状态 变更，删除redis中用户绑定的角色跟权限
        if (!sysUser.getUsername().equals(vo.getUsername())
                || (!StringUtils.isEmpty(vo.getPassword())
                && !sysUser.getPassword().equals(PasswordUtils.encode(vo.getPassword(), sysUser.getSalt())))
                || !sysUser.getStatus().equals(vo.getStatus())) {
            httpSessionService.abortUserById(vo.getId());
        }

        if (!StringUtils.isEmpty(vo.getPassword())) {
            String newPassword = PasswordUtils.encode(vo.getPassword(), sysUser.getSalt());
            vo.setPassword(newPassword);
        } else {
            vo.setPassword(null);
        }
        vo.setUpdateId(httpSessionService.getCurrentUserId());
        sysUserMapper.updateById(vo);

    }

    @Override
    public void updateUserInfoMy(SysUser vo) {


        SysUser user = sysUserMapper.selectById(httpSessionService.getCurrentUserId());
        if (null == user) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if (!StringUtils.isEmpty(vo.getPassword())) {
            String newPassword = PasswordUtils.encode(vo.getPassword(), user.getSalt());
            vo.setPassword(newPassword);
//            user.setPassword(newPassword);
        } else {
            vo.setPassword(null);
//            user.setPassword(null);
        }
        vo.setUpdateId(httpSessionService.getCurrentUserId());
        vo.setPassword(null);
        user.setUpdateId(httpSessionService.getCurrentUserId());
        BeanUtils.copyProperties(vo,user, JUNUtil.getNullPropertyNames(vo));
        sysUserMapper.updateById(user);

    }



    @Override
    public IPage<SysUser> pageInfo(SysUser vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getUsername())) {
            queryWrapper.like(SysUser::getUsername, vo.getUsername());
        }
        if (!StringUtils.isEmpty(vo.getStartTime())) {
            queryWrapper.gt(SysUser::getCreateTime, vo.getStartTime());
        }
        if (!StringUtils.isEmpty(vo.getEndTime())) {
            queryWrapper.lt(SysUser::getCreateTime, vo.getEndTime());
        }
        if (!StringUtils.isEmpty(vo.getNickName())) {
            queryWrapper.like(SysUser::getNickName, vo.getNickName());
        }
        if (!StringUtils.isEmpty(vo.getRealName())) {
        	queryWrapper.or();
        	queryWrapper.like(SysUser::getRealName, vo.getRealName());
        }
        if (null != vo.getStatus()) {
            queryWrapper.eq(SysUser::getStatus, vo.getStatus());
        }
        if (!StringUtils.isEmpty(vo.getDeptNo())) {
            LambdaQueryWrapper<SysDept> queryWrapperDept = Wrappers.lambdaQuery();
            queryWrapperDept.select(SysDept::getId).like(SysDept::getRelationCode, vo.getDeptNo());
            List<Object> list = sysDeptMapper.selectObjs(queryWrapperDept);
            queryWrapper.in(SysUser::getDeptId, list);
        }
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(vo.getCreateIds())) {
            queryWrapper.in(SysUser::getId, vo.getCreateIds());
        }
        queryWrapper.orderByAsc(SysUser::getId);
        IPage<SysUser> iPage = sysUserMapper.selectPage(page, queryWrapper);
        LambdaQueryWrapper<SysDept> queryWrapper2 = Wrappers.lambdaQuery();
        List<SysDept> depts = sysDeptMapper.selectList(queryWrapper2);
        if (!CollectionUtils.isEmpty(iPage.getRecords())) {
            for (SysUser sysUser : iPage.getRecords()) {
                //SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
//                if (sysDept != null) {
//                    sysUser.setDeptName(sysDept.getName());
//                }
            	depts.forEach(item->{
            		if(item.getId().equalsIgnoreCase(sysUser.getDeptId())) {
            			SysDept sysDept = item;
            			sysUser.setDeptName(sysDept.getName());
            		}
//            		if(item.getManagerName().equalsIgnoreCase(sysUser.getRealName())) {
//            			SysDept sysDept = item;
//            			sysUser.setDeptName(sysDept.getName());
//            		}
            	});
            }
        }
        return iPage;
    }
    
    @Override
    public IPage<SysUser> pageInfoApprover(SysUser vo) {
    	Page page = new Page(vo.getPage(), vo.getLimit());
    	LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
//    	if (!StringUtils.isEmpty(vo.getRealName())) {
//    		if(!queryWrapper.isEmptyOfWhere()) {
//    			queryWrapper.or();
//    		}
//    		queryWrapper.like(SysUser::getRealName, vo.getRealName());
//    	}
    	if (!StringUtils.isEmpty(vo.getStartTime())) {
    		queryWrapper.gt(SysUser::getCreateTime, vo.getStartTime());
    	}
    	if (!StringUtils.isEmpty(vo.getEndTime())) {
    		queryWrapper.lt(SysUser::getCreateTime, vo.getEndTime());
    	}
    	if (!StringUtils.isEmpty(vo.getNickName())) {
    		if(!queryWrapper.isEmptyOfWhere()) {
    			queryWrapper.or();
    		}
    		queryWrapper.like(SysUser::getNickName, vo.getNickName());
    	}
    	if (!StringUtils.isEmpty(vo.getUsername())) {
    		queryWrapper.like(SysUser::getUsername, vo.getUsername());
    	}
    	if (!StringUtils.isEmpty(vo.getRealName())) {
    		if(!queryWrapper.isEmptyOfWhere()) {
    			queryWrapper.or();
    		}
    		queryWrapper.like(SysUser::getRealName, vo.getRealName());
    	}
    	if (null != vo.getStatus()) {
    		queryWrapper.eq(SysUser::getStatus, vo.getStatus());
    	}
    	if (!StringUtils.isEmpty(vo.getDeptNo())) {
    		LambdaQueryWrapper<SysDept> queryWrapperDept = Wrappers.lambdaQuery();
    		queryWrapperDept.select(SysDept::getId).like(SysDept::getRelationCode, vo.getDeptNo());
    		List<Object> list = sysDeptMapper.selectObjs(queryWrapperDept);
    		queryWrapper.in(SysUser::getDeptId, list);
    	}
    	//查询部门数据
    	LambdaQueryWrapper<SysDept> queryWrapper2 = Wrappers.lambdaQuery();
    	List<SysDept> deptManager = sysDeptMapper.selectList(queryWrapper2);
    	for(SysDept item : deptManager) {
    		if (!StringUtils.isEmpty(item.getManagerName())) {
    			if (StringUtils.isEmpty(vo.getRealName())||StringUtils.isEmpty(vo.getUsername())) {
    				queryWrapper.or();
    				queryWrapper.in(SysUser::getRealName, item.getManagerName());
    			}
        	}
		}
    	
    	//查询用户数据
    	queryWrapper.orderByAsc(SysUser::getId);
    	IPage<SysUser> iPage = sysUserMapper.selectPage(page, queryWrapper);
    	
    	//设置用户部门
    	if (!CollectionUtils.isEmpty(iPage.getRecords())) {
    		for (SysUser sysUser : iPage.getRecords()) {
    			for(SysDept item : deptManager) {
    				if(item.getId().equalsIgnoreCase(sysUser.getDeptId())) {
    					SysDept sysDept = item;
    					sysUser.setDeptName(sysDept.getName());
    				}
    			}
    		}
    	}
    	return iPage;
    }
    

	@Override
    public IPage<SysUser> pageInfoApproverByRole(SysUser vo) {
    	Page page = new Page(vo.getPage(), vo.getLimit());
    	String processName = vo.getProcessName();
    	String taskName = vo.getTaskName();
    	String roleName = "";
    	if(!StringUtils.isEmpty(taskName)) {
    		if(taskName.contains("部门经理")) {
    			roleName = "部门经理";
    		}else if(taskName.contains("总经理")) {
    			roleName = "总经理";
    		}else if(taskName.contains("董事长")) {
    			roleName = "董事长";
    		}
    	}
    	LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
    	if (!StringUtils.isEmpty(vo.getUsername())) {
    		queryWrapper.like(SysUser::getUsername, vo.getUsername());
    	}
    	if (!StringUtils.isEmpty(vo.getRealName())) {
    		if(!queryWrapper.isEmptyOfWhere()) {
    			queryWrapper.or();
    		}
    		queryWrapper.like(SysUser::getRealName, vo.getRealName());
    	}
    	queryWrapper.apply("  id in (  "
    			+ "SELECT u.id from sys_user u   "
    			+ "INNER JOIN sys_user_role ur on u.id = ur.user_id  "
    			+ "INNER JOIN sys_role r on ur.role_id = r.id  "
    			+ "where r.id in (  "
    			+ "SELECT r1.id from sys_role r1 where r.name like '%"+roleName+"%' )  "
    			+ ") ", null);
    	IPage<SysUser> iPage = sysUserMapper.selectPage(page, queryWrapper);
    	return iPage;
    }

    @Override
    public void addUser(SysUser vo) {

        SysUser sysUserOne = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, vo.getUsername()));
        if (sysUserOne != null) {
            throw new BusinessException("用户已存在，请勿重复添加！");
        }
        vo.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(vo.getPassword(), vo.getSalt());
        vo.setPassword(encode);
        vo.setStatus(1);
        vo.setCreateWhere(1);
        sysUserMapper.insert(vo);
        if (!CollectionUtils.isEmpty(vo.getRoleIds())) {
            UserRoleOperationReqVO reqVO = new UserRoleOperationReqVO();
            reqVO.setUserId(vo.getId());
            reqVO.setRoleIds(vo.getRoleIds());
            userRoleService.addUserRoleInfo(reqVO);
        }
    }

    @Override
    public void updatePwd(SysUser vo) {

        SysUser sysUser = sysUserMapper.selectById(vo.getId());
        if (sysUser == null) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if ("test".equals(env) && "guest".equals(sysUser.getUsername())) {
            throw new BusinessException("演示环境禁止修改演示账号密码");
        }

        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getOldPwd(), sysUser.getPassword())) {
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        if (sysUser.getPassword().equals(PasswordUtils.encode(vo.getNewPwd(), sysUser.getSalt()))) {
            throw new BusinessException("新密码不能与旧密码相同");
        }
        sysUser.setPassword(PasswordUtils.encode(vo.getNewPwd(), sysUser.getSalt()));
        sysUserMapper.updateById(sysUser);
        //退出用户
        httpSessionService.abortAllUserByToken();

    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        List<String> roleIdsByUserId = userRoleService.getRoleIdsByUserId(userId);
        List<SysRole> list = roleService.list();
        UserOwnRoleRespVO vo = new UserOwnRoleRespVO();
        vo.setAllRole(list);
        vo.setOwnRoles(roleIdsByUserId);
        return vo;
    }
}
