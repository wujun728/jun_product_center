package me.wuwenbin.noteblogv5.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import me.wuwenbin.noteblogv5.enums.ObjectKeyEnum;
import me.wuwenbin.noteblogv5.constant.UploadConstant;
import me.wuwenbin.noteblogv5.model.entity.Upload;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 上传方法
 * created by Wuwenbin on 2018/7/17 at 10:29
 *
 * @author wuwenbin
 */
public interface UploadService<T> extends IService<Upload> {

    /**
     * layui上传组件
     */
    String LAYUI_UPLOADER = "lay";

    /**
     * nkeditor 上传组件
     */
    String NKEDITOR_UPLOADER = "nk";

    /**
     * editorMd上传组件
     */
    String EDITORMD_UPLOADER = "md";

    /**
     * 默认是本地服务器上传
     * 如果需要其他方式，请在实现类中重写该方法
     *
     * @return 返回默认的上传方式
     */
    default UploadConstant.Method getUploadMethod() {
        return UploadConstant.Method.LOCAL;
    }

    /**
     * 默认上传方法
     *
     * @param sessionUserId
     * @param fileObj
     * @param paramMap
     * @return
     * @throws IOException
     */
    default Upload baseUpload(Long sessionUserId, MultipartFile fileObj, Map<Object, Object> paramMap) throws IOException {
        String uploadPathPre = Objects.requireNonNull(fileObj.getContentType()).contains("image/") ? UploadConstant.FileType.IMAGE : UploadConstant.FileType.FILE;
        String fileName = fileObj.getOriginalFilename();
        //扩展名，包括点符号
        assert fileName != null;
        String ext = fileName.substring(Objects.requireNonNull(fileName).lastIndexOf("."));
        String newFileName = IdUtil.randomUUID().concat(ext);
        String prefix = NbUtils.getBean(Environment.class).getProperty("app.upload.path");
        String datePrefix = LocalDate.now().toString();
        String completePrefix = prefix + uploadPathPre + "/" + datePrefix + "/";
        //剔除字符串前缀L：[file:]
        File targetFile = new File(completePrefix.substring(5));
        boolean m = true;
        if (!targetFile.exists()) {
            m = targetFile.mkdirs();
        }
        String uploadFilePath;
        if (m) {
            uploadFilePath = FileUtil.getAbsolutePath(completePrefix + newFileName);
        } else {
            throw new RuntimeException("创建目录：" + completePrefix + "失败！");
        }
        FileOutputStream out = new FileOutputStream(uploadFilePath);
        out.write(fileObj.getBytes());
        out.flush();
        out.close();
        String virtualPath = UploadConstant.FileType.VISIT_PATH.concat(uploadPathPre).concat("/" + datePrefix + "/").concat(newFileName);
        Upload u = Upload.builder()
                .diskPath(uploadFilePath)
                .virtualPath(virtualPath)
                .upload(new Date())
                .type(fileObj.getContentType())
                .userId(sessionUserId)
                .build();
        Object okc = paramMap.get("objectKey");
        if (ObjectUtil.isNotEmpty(okc)) {
            u.setObjectKey(ObjectKeyEnum.getObjectKeyEnumByCode(NumberUtils.parseNumber(okc.toString(), Integer.class)));
        }
        Object okcId = paramMap.get("objectKeyId");
        if (ObjectUtil.isNotEmpty(okcId)) {
            u.setObjectKeyId(okcId.toString());
        }
        return u;
    }

    /**
     * 文件上传接口方法
     *
     * @param sessionUserId
     * @param fileObj       文件对象
     * @param reqType       上传组件（layuiUploader还是NKuploader）
     * @param paramMap      上传之外的额外参数
     * @return upload 的上传json
     */
    T doUpload(Long sessionUserId, MultipartFile fileObj, String reqType, Map<Object, Object> paramMap);

}
