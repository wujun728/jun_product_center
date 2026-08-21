package com.ruoyi.file.task;

import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 本地文件缓存删除任务
 * @Author wocurr.com
 */
@Slf4j
@Component("deleteTempfileTask")
public class DeleteTempfileTask {

    /**
     * 删除本地缓存文件<br>
     *  1、每个月底执行一次；
     *  2、删除上个月的缓存文件
     */
    public void deleteTempfile() {
        try {
            log.info("## 定时任务-删除缓存文件：开始执行删除本地缓存任务。。。");
            FileStorageUtils.deleteTempFile();
            log.info("## 定时任务-删除缓存文件：执行删除本地缓存完成！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
