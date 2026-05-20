package com.ruoyi.serial.task;

import com.ruoyi.serial.service.ICodeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 编号重置任务
 * @Author wocurr.com
 */
@Slf4j
@Component("codeRestTask")
public class CodeRestTask {

    @Autowired
    private ICodeConfigService codeConfigService;

    /**
     * 重置最新流水号任务
     */
    public void restSeq() {
        try {
            log.info("## 定时任务-重置编号流水号开始...");
            codeConfigService.restSeq();
            log.info("## 定时任务-重置编号流水号完成！");
        } catch (Exception e) {
            log.error("## 每日任务-重置编号流水号失败！");
            log.error(e.getMessage(), e);
        }
    }
}
