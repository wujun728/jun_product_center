package me.wuwenbin.noteblogv5.service.impl.upload;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.constant.UploadConstant;
import me.wuwenbin.noteblogv5.constant.uploader.LayUploader;
import me.wuwenbin.noteblogv5.constant.uploader.MdUploader;
import me.wuwenbin.noteblogv5.constant.uploader.NkUploader;
import me.wuwenbin.noteblogv5.mapper.UploadMapper;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Upload;
import me.wuwenbin.noteblogv5.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * created by Wuwenbin on 2018/7/17 at 10:33
 * 本地上传的实现类
 *
 * @author wuwenbin
 */
@Slf4j
@Service("localUpload")
@Transactional(rollbackFor = Exception.class)
public class LocalUploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService<Object> {

    private final UploadMapper uploadMapper;

    @Autowired
    public LocalUploadServiceImpl(UploadMapper uploadMapper) {
        this.uploadMapper = uploadMapper;
    }

    @Override
    public UploadConstant.Method getUploadMethod() {
        return UploadConstant.Method.LOCAL;
    }

    @Override
    public Object doUpload(Long sessionUserId, MultipartFile fileObj, String reqType, Map<Object, Object> paramMap) {
        try {
            Upload upload = baseUpload(sessionUserId, fileObj, paramMap);
            uploadMapper.insert(upload);
            if (LAYUI_UPLOADER.equalsIgnoreCase(reqType)) {
                return new LayUploader().ok("上传成功！", upload.getVirtualPath());
            } else if (NKEDITOR_UPLOADER.equalsIgnoreCase(reqType)) {
                return new NkUploader().ok("上传成功！", upload.getVirtualPath());
            } else if (EDITORMD_UPLOADER.equalsIgnoreCase(reqType)) {
                return new MdUploader().ok("上传成功！", upload.getVirtualPath());
            } else {
                return ResultBeanObj.ok("上传成功！", upload.getVirtualPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传图片/文件失败，错误信息：{}", e.getMessage());
            if (LAYUI_UPLOADER.equalsIgnoreCase(reqType)) {
                return new LayUploader().err("上传图片/文件失败，错误信息：" + e.getLocalizedMessage());
            } else if (NKEDITOR_UPLOADER.equalsIgnoreCase(reqType)) {
                return new NkUploader().err("上传图片/文件失败，错误信息：" + e.getLocalizedMessage());
            } else if (EDITORMD_UPLOADER.equalsIgnoreCase(reqType)) {
                return new MdUploader().err("上传图片/文件失败，错误信息：" + e.getLocalizedMessage());
            } else {
                return ResultBeanObj.error("上传图片/文件失败，错误信息：" + e.getLocalizedMessage());
            }
        }
    }
}
