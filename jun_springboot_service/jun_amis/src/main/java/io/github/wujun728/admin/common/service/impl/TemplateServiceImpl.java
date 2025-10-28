package io.github.wujun728.admin.common.service.impl;

import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.config.UserSession;
import io.github.wujun728.admin.common.service.SerialNumberService;
import io.github.wujun728.admin.common.service.TemplateService;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.constants.UserType;
import io.github.wujun728.admin.rbac.data.Permission;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("templateService")
@Slf4j
public class TemplateServiceImpl implements TemplateService {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private PageService pageService;
    @Resource
    private SerialNumberService serialNumberService;
    @Override
    public String findAllParent(String childSql, String tableName) {
        childSql = pageService.getQuerySql(childSql);

        String nextChildSql = childSql;
        Set<Long> parentIds = new HashSet<>();
        List<Long> childIds = jdbcService.findForObject(childSql, Long.class);
        parentIds.addAll(childIds);
        if(childIds.isEmpty()){
            return "-1";
        }
        String parentSql = "select parent_id from "+tableName+" where id in ({}) and parent_id is not null ";
        while (true){
            String sql = StrUtil.format(parentSql,nextChildSql);
            List<Long> ids = jdbcService.findForObject(sql, Long.class);
            parentIds.addAll(ids);
            if(ids.isEmpty()){
                break;
            }
            nextChildSql = StringUtil.concatStr(ids,",");
        }
        return StringUtil.concatStr(parentIds,",");
    }

    @Override
    public String permission(String permissionCode, String field) {
        return this.permission(permissionCode,field,null);
    }

    private String permission(String permissionCode, String field,String tableName) {
        UserSession session = SessionContext.getSession();
        if(UserType.Admin.equals(session.getUserType())){
            return "";
        }
        Permission permission = jdbcService.findOne(Permission.class, "code", permissionCode);
        if(permission == null){
            return " and 1=-1 \n -- 权限编号错误"+permissionCode+" \n";
        }
        List<String> configValues = jdbcService.findForObject("select distinct t.config_value from (" +
                        //岗位数据权限
                        "select config_value from position_permission " +
                        "where permission_id = ? " +
                        "and enterprise_id = ? " +
                        "and position_id in " +
                        "(" +
                        "select position_id from enterprise_user_position where enterprise_user_id in " +
                        "(" +
                        "select id from enterprise_user where user_id = ? and enterprise_id = ? " +
                        ")" +
                        ") union all " +
                        // 部门数据权限
                        "select config_value from dept_permission " +
                        "where permission_id = ? " +
                        "and dept_id in (" +
                        "select dept_id from enterprise_user where user_id = ? and enterprise_id = ? " +
                        "and dept_id is not null" +
                        ") union all " +
                        //用户数据权限
                        "select config_value from user_permission " +
                        "where permission_id = ? " +
                        "and enterprise_user_id in (" +
                        "select id from enterprise_user where user_id = ? and enterprise_id = ? " +
                        "and dept_id is not null" +
                        ")" +

                        ") t"
                , String.class,
                //岗位数据权限
                permission.getId(),
                session.getEnterpriseId(),
                session.getUserId(),
                session.getEnterpriseId(),
                //部门数据权限
                permission.getId(),
                session.getUserId(),
                session.getEnterpriseId(),
                //用户数据权限
                permission.getId(),
                session.getUserId(),
                session.getEnterpriseId()
        );
        if(configValues.isEmpty()){
            return " and 1=-1 \n -- 没有配置数据权限"+permissionCode+" \n";
        }
        if("dic".equals(permission.getPermissionType()) && "selfOrAll".equals(permission.getPermissionValue())){
            //查看自己/全部
            if(configValues.contains("all")){
                return "";
            }
            return " and "+field+"="+session.getUserId()+" ";
        }else if("dic".equals(permission.getPermissionType()) && "dept".equals(permission.getPermissionValue())){
            //部门数据权限
            if(configValues.contains("all")){
                return "";
            }
            if(configValues.contains("dept") && configValues.contains("deptAndChild")){
                //所属,所属及子部门
                configValues.remove("dept");
            }
            if(configValues.contains("manageDept") && configValues.contains("manageDeptAndChild")){
                //负责,负责及子部门
                configValues.remove("manageDept");
            }
            Set<Long> ids = new HashSet<>();
            for(String value:configValues){
                if("dept".equals(value)){
                    List<Long> _ids = jdbcService.findForObject("select dept_id from enterprise_user " +
                            "where user_id = ? " +
                            "and enterprise_id = ? " +
                            "and dept_id is not null", Long.class, session.getUserId(), session.getEnterpriseId());
                    ids.addAll(_ids);
                }else if("deptAndChild".equals(value)){
                    Set<Long> _ids = jdbcService.findChildIds(StrUtil.format("select dept_id from enterprise_user " +
                                    "where user_id = {} " +
                                    "and enterprise_id = {} " +
                                    "and dept_id is not null",
                            session.getUserId(),
                            session.getEnterpriseId()), "select id from dept where parent_id in ({})");
                    ids.addAll(_ids);
                }else if("manageDept".equals(value)){
                    List<Long> _ids = jdbcService.findForObject("select dept_id from dept_manager where enterprise_user_id in(" +
                            "select id from enterprise_user " +
                            "where user_id = ? " +
                            "and enterprise_id = ? " +
                            "and dept_id is not null" +
                            ")",Long.class,session.getUserId(),session.getEnterpriseId());
                    ids.addAll(_ids);
                }else if("manageDeptAndChild".equals(value)){
                    Set<Long> _ids = jdbcService.findChildIds(StrUtil.format("select dept_id from dept_manager where enterprise_user_id in(" +
                                    "select id from enterprise_user " +
                                    "where user_id = {} " +
                                    "and enterprise_id = {} " +
                                    "and dept_id is not null" +
                                    ")",
                            session.getUserId(),
                            session.getEnterpriseId()), "select id from dept where parent_id in ({})");
                    ids.addAll(_ids);
                }
            }
            if(ids.isEmpty()){
                return "and 1=-1 \n -- 配置的部门数据权限"+permissionCode+"关联不到部门 \n";
            }
            return StrUtil.format(" and {} in ({}) ",field,StringUtil.concatStr(ids,","));
        }
        if(StringUtils.isBlank(tableName)){
            return StrUtil.format(" and {} in ({}) ",field,StringUtil.concatStr(configValues,",","'"));
        }
        return StrUtil.format("and {} in ({})",
                field,
                this.findAllParent(StrUtil.format("select id from {} where id in({})",tableName,StringUtil.concatStr(configValues,",")),tableName)
        );
    }

    @Override
    public String permissionTree(String permissionCode, String field, String tableName) {
        return this.permission(permissionCode,field,tableName);
    }

    @Override
    public String serial(String code) {
        return serialNumberService.nextSerial(code);
    }

    @Override
    public String treeSql(String template, String tableName, String ids) {
        if(StringUtils.isBlank(ids)){
            return "";
        }
        String errSql = StrUtil.format(template,"-1");
        Set<Long> parentIds = new HashSet<>();
        String[] idArr = StringUtil.splitStr(ids, ",");
        for(String id:idArr){
            try {
                parentIds.add(Long.parseLong(id));
            }catch (Exception e){
                log.error("错误的id值"+id);
                return errSql;
            }
        }
        String childSql = "select id from "+tableName+" where parent_id in ({}) ";
        Set<Long> childIds = jdbcService.findChildIds(parentIds, childSql);
        if(!childIds.isEmpty()){
            return StrUtil.format(template,StringUtil.concatStr(childIds,","));
        }else{
            return errSql;
        }
    }

    @Override
    public String addParam(String sql, Object param, List<Object> values) {
        if(param == null || ((param instanceof String) && (StringUtils.isBlank((String)param) || ((String)param).startsWith("$")))){
            return "";
        }
        values.add(param);
        return sql;
    }

    @Override
    public String addParamLike(String sql, Object param, List<Object> values) {
        if(param == null || ((param instanceof String) && (StringUtils.isBlank((String)param) || ((String)param).startsWith("$")))){
            return "";
        }
        values.add(StrUtil.format("%{}%",param));
        return sql;
    }
}
