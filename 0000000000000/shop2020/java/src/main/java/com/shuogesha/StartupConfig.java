package com.shuogesha;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.core.rule.TableRule;
import org.apache.shardingsphere.core.strategy.route.ShardingStrategy;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

@Component
public class StartupConfig {
    @Autowired
    private DataSource dataSource;

//    @PostConstruct
//    public void init() {
//        this.loadLogInit();
//    }
//
//    private void loadLogInit() {
//        if (dataSource instanceof ShardingDataSource) {
//            ShardingDataSource sds = (ShardingDataSource) dataSource; 
//             
//            ShardingContext shardingContext = sds.getShardingContext();
//            ShardingRule shardingRule = shardingContext.getShardingRule();
//            Optional<TableRule> systemLog = shardingRule.findTableRule("system_log");
//            TableRule tableRule = systemLog.orNull();
//            if (tableRule != null) {
//                ShardingStrategy tableShardingStrategy = tableRule.getTableShardingStrategy();
//                LogShardingAlgorithm preciseShardingAlgorithm = (LogShardingAlgorithm) ReflectUtil.getFieldValue(tableShardingStrategy, "preciseShardingAlgorithm");
//                LogShardingAlgorithm rangeShardingAlgorithm = (LogShardingAlgorithm) ReflectUtil.getFieldValue(tableShardingStrategy, "rangeShardingAlgorithm");
//                preciseShardingAlgorithm.init();
//                rangeShardingAlgorithm.init();
//            }
//        }
//    }
}
