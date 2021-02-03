package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.mapper.DownloadMapper;
import me.wuwenbin.noteblogv5.model.entity.Download;
import me.wuwenbin.noteblogv5.service.DownloadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DownloadServiceImpl extends ServiceImpl<DownloadMapper, Download> implements DownloadService {
}
