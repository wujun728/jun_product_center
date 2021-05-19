package com.platform.aop;

import com.platform.annotation.DataFilter;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysRoleDeptService;
import com.platform.utils.Constant;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@Aspect
@Component
public class DataFilterAspect {
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Pointcut("@annotation(com.platform.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        //获取参数
        Object params = point.getArgs()[0];
        if (params != null && params instanceof Map) {
            SysUserEntity user = ShiroUtils.getUserEntity();

            //如果不是超级管理员，则只能查询本部门及子部门数据
            if (!Constant.SUPER_ADMIN.equals(user.getUserId())) {
                Map map = (Map) params;
                map.put("filterSql", getFilterSQL(user, point));
            }

            return;
        }

        throw new RRException("数据权限接口的参数必须为Map类型，且不能为NULL");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getFilterSQL(SysUserEntity user, JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);

        String userAlias = dataFilter.userAlias();
        String deptAlias = dataFilter.deptAlias();

        StringBuilder filterSql = new StringBuilder();

        //取出登录用户部门权限
        String alias = getAliasByUser(user.getUserId());
        if (StringUtils.isNotEmpty(alias)) {
            filterSql.append(" and (");
            filterSql.append(deptAlias);
            filterSql.append(" in ");
            filterSql.append(" ( ");
            filterSql.append(alias);
            filterSql.append(" ) ");
            if (StringUtils.isNotBlank(userAlias)) {
                filterSql.append(" or ");
                filterSql.append(userAlias);
                filterSql.append("=");
                filterSql.append(user.getUserId());
            }
            filterSql.append(" ) ");
        } else if (StringUtils.isNotBlank(userAlias)) {
            filterSql.append(" and ");
            filterSql.append(userAlias);
            filterSql.append("=");
            filterSql.append(user.getUserId());
            filterSql.append(" ");
        }
        //没有部门数据权限，也能查询本人数据
        if (dataFilter.self()) {
            filterSql.append(" or ");
            filterSql.append(userAlias);
            filterSql.append("=");
            filterSql.append(user.getUserId());
            filterSql.append(" ");
        }

        return filterSql.toString();
    }

    /**
     * 取出用户权限
     *
     * @param userId 登录用户Id
     * @return 权限
     */
    private String getAliasByUser(String userId) {
        @SuppressWarnings("unchecked")
        List<String> roleOrglist = sysRoleDeptService.queryDeptIdListByUserId(userId);
        StringBuilder roleStr = new StringBuilder();
        String alias = "";
        if (roleOrglist != null && !roleOrglist.isEmpty()) {
            for (String roleId : roleOrglist) {
                roleStr.append(",");
                roleStr.append("'");
                roleStr.append(roleId);
                roleStr.append("'");
            }
            alias = roleStr.toString().substring(1, roleStr.length());
        }
        return alias;
    }
}
