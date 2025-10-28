package io.github.wujun728.admin.common.config;

import io.github.wujun728.admin.common.service.impl.AbstractCacheService;
import io.github.wujun728.admin.page.controller.TestAmisController;
import io.github.wujun728.admin.page.service.DicCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

@Component
@ConditionalOnProperty(value="jqp.reset",havingValue = "true")
@EnableAsync
@Slf4j
public class ResetDb {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DicCacheService dicCacheService;

    {
        log.info("重置Bean");
    }
//    @Scheduled(fixedDelay = 3000)
//    @Scheduled(fixedRate = 3000)
//    @Scheduled(cron = "0 */5 * * * ?")
    @Scheduled(cron = "0 0 1 * * ?")
//    @Async(value = "myAsync")
    void resetDb() throws Exception {
        log.info("开始还原数据库");
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setAutoCommit(true);
        InputStream in = TestAmisController.class.getClassLoader().getResourceAsStream("db/jqp.sql");
        runner.setFullLineDelimiter(false);
        runner.setDelimiter(";");//语句结束符号设置
        //runner.setLogWriter(null);//日志数据输出，这样就不会输出过程
        runner.setSendFullScript(false);
        runner.setAutoCommit(true);
        runner.setStopOnError(true);
        runner.runScript(new InputStreamReader(in, "utf8"));

        log.info("结束还原数据库");
        log.info("开始清理缓存---");
        dicCacheService.clear();
        AbstractCacheService.clearAll();
        log.info("结束清理缓存---");
    }

}
