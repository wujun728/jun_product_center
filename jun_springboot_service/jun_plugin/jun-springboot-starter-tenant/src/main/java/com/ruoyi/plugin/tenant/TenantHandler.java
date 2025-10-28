package com.ruoyi.plugin.tenant;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.ruoyi.plugin.tenant.config.TenantConfigProperties;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;

import java.util.HashSet;
import java.util.Set;

/**
 * 租户维护处理器
 *
 * @author wangzongrun
 */
@Slf4j
public class TenantHandler implements TenantLineHandler {

    /**
     * 租户列名
     */
    private String column;

    /**
     * 不忽略的表
     */
    private final Set<String> noIgnoreTables = new HashSet<>();

    public TenantHandler(TenantConfigProperties properties) {
        this.column = properties.getColumn();
        // 不同 DB 下，大小写的习惯不同，所以需要都添加进去
        properties.getNoIgnoreTables().forEach(table -> {
            noIgnoreTables.add(table.toLowerCase());
            noIgnoreTables.add(table.toUpperCase());
        });
        // 在 OracleKeyGenerator 中，生成主键时，会查询这个表，查询这个表后，会自动拼接 TENANT_ID 导致报错
        noIgnoreTables.add("DUAL");

    }

    /**
     * 获取租户 ID 值表达式，只支持单个 ID 值
     * <p>
     *
     * @return 租户 ID 值表达式
     */
    @Override
    public Expression getTenantId() {
        Integer tenantId = TenantContextHolder.getTenantId();
        log.debug("当前租户为 >> {}", tenantId);

        if (tenantId == null) {
            return new NullValue();
        }
        return new LongValue(tenantId);
    }

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return column;
    }

    /**
     * 根据表名判断是否忽略拼接多租户条件
     * 默认都要进行解析并拼接多租户条件
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
     */
    @Override
    public boolean ignoreTable(String tableName) {
        Integer tenantId = TenantContextHolder.getTenantId();
        // 租户中ID 为空，查询全部，不进行过滤
        if (tenantId == null) {
            return Boolean.TRUE;
        }
        // 情况一，全局忽略多租户; 情况二，忽略多租户的表
        return TenantContextHolder.isIgnore() || !CollUtil.contains(noIgnoreTables, tableName);
    }

}
