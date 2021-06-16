package me.wuwenbin.noteblogv5.service.impl.upload;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.ObjectKeyEnum;
import me.wuwenbin.noteblogv5.constant.UploadConstant;
import me.wuwenbin.noteblogv5.constant.uploader.LayUploader;
import me.wuwenbin.noteblogv5.constant.uploader.MdUploader;
import me.wuwenbin.noteblogv5.constant.uploader.NkUploader;
import me.wuwenbin.noteblogv5.mapper.ParamMapper;
import me.wuwenbin.noteblogv5.mapper.UploadMapper;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.model.entity.Upload;
import me.wuwenbin.noteblogv5.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * 七牛文件上传Service
 * created by Wuwenbin on 2018/7/17 at 14:46
 *
 * @author wuwenbin
 */
@Slf4j
@Service("qiniuUpload")
@Transactional(rollbackFor = Exception.class)
public class QiniuUploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService<Object> {

    private final ParamMapper paramMapper;

    @Autowired
    public QiniuUploadServiceImpl(ParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    @Override
    public UploadConstant.Method getUploadMethod() {
        return UploadConstant.Method.QINIU;
    }

    @Override
    public Object doUpload(Long sessionUserId, MultipartFile fileObj, String reqType, Map<Object, Object> paramMap) {
        log.info("上传[" + fileObj.getContentType() + "]类型文件");
        Response res = doUpload(fileObj, getUpToken());
        String message;
        try {
            if (res != null && res.isOK()) {
                JSONObject respObj = JSONUtil.parseObj(res.bodyString());
                String generateFileName = respObj.getStr("key");
                String qiniuDomain = paramMapper.selectOne(Wrappers.<Param>query().eq("name", NBV5.QINIU_DOMAIN)).getValue();
                log.info("上传至七牛云服务器成功！，文件：[{}]", generateFileName);
                String src = qiniuDomain + "/" + generateFileName;
                if (LAYUI_UPLOADER.equalsIgnoreCase(reqType)) {
                    return new LayUploader().ok("上传至七牛云服务器成功！", src);
                } else if (NKEDITOR_UPLOADER.equalsIgnoreCase(reqType)) {
                    return new NkUploader().ok("上传成功！", src);
                } else if (EDITORMD_UPLOADER.equalsIgnoreCase(reqType)) {
                    return new MdUploader().ok("上传成功！", src);
                } else {
                    return ResultBeanObj.ok("上传成功！", src);
                }
            } else {
                message = res != null ? res.error : "上传至七牛云服务失败，返回信息为空！";
            }
        } catch (QiniuException e) {
            message = StrUtil.format("上传至七牛云服务器失败，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }
        log.error("上传至七牛服务器失败，错误信息：[{}]", message);
        if (LAYUI_UPLOADER.equalsIgnoreCase(reqType)) {
            return new LayUploader().err(message);
        } else if (NKEDITOR_UPLOADER.equalsIgnoreCase(reqType)) {
            return new NkUploader().err("上传至七牛服务器失败，错误信息：" + message);
        } else if (EDITORMD_UPLOADER.equalsIgnoreCase(reqType)) {
            return new MdUploader().err("上传至七牛服务器失败，错误信息：" + message);
        } else {
            return ResultBeanObj.error("上传至七牛服务器失败，错误信息：" + message);
        }
    }

    @Override
    public Upload baseUpload(Long sessionUserId, MultipartFile fileObj, Map<Object, Object> paramMap) {
        log.info("上传[" + fileObj.getContentType() + "]类型文件");
        Response res = doUpload(fileObj, getUpToken());
        try {
            if (res != null && res.isOK()) {
                JSONObject respObj = JSONUtil.parseObj(res.bodyString());
                String generateFileName = respObj.getStr("key");
                String qiniuDomain = paramMapper.selectOne(Wrappers.<Param>query().eq("name", NBV5.QINIU_DOMAIN)).getValue();
                String src = qiniuDomain + "/" + generateFileName;
                log.info("上传至七牛云服务器成功！，文件：[{}]", generateFileName);
                Upload u = Upload.builder()
                        .diskPath("")
                        .virtualPath(src)
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
            } else {
                throw new RuntimeException("上传文件至七牛云失败" + (res != null ? "，" + res.error : ""));
            }
        } catch (QiniuException e) {
            throw new RuntimeException("上传文件至七牛云失败", e);
        }

    }

    /**
     * 获取uptoken
     *
     * @return
     */
    private String getUpToken() {
        String accessKey = paramMapper.selectOne(Wrappers.<Param>query().eq("name", NBV5.QINIU_ACCESS_KEY)).getValue();
        String secretKey = paramMapper.selectOne(Wrappers.<Param>query().eq("name", NBV5.QINIU_SECRET_KEY)).getValue();
        String bucketName = paramMapper.selectOne(Wrappers.<Param>query().eq("name", NBV5.QINIU_BUCKET)).getValue();
        //密钥配置
        Auth auth = Auth.create(accessKey, secretKey);
        //简单上传，使用默认策略，只需要设置上传的空间名就可以了
        return auth.uploadToken(bucketName);
    }

    /**
     * 七牛上传
     *
     * @param file    文件
     * @param uptoken 上传密钥
     * @return Response
     */
    private Response doUpload(MultipartFile file, String uptoken) {
        try {
            String resId = IdUtil.randomUUID();
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Region.autoRegion());
            String extend = fileName.substring(fileName.lastIndexOf("."));
            //调用put方法上传
            Response res = new UploadManager(cfg).put(file.getBytes(), resId.concat(extend), uptoken);
            log.info("[七牛上传文件] - [{}] - 返回信息：{}", res.isOK(), res.bodyString());
            return res;
        } catch (QiniuException e) {
            Response re = e.response;
            log.error("[七牛上传文件] - [{}] - 异常信息：{}", re.isOK(), re.toString());
            try {
                log.error(" 响应异常文本信息：{}", re.bodyString());
            } catch (QiniuException ignored) {
            }
            return re;
        } catch (Exception ex) {
            log.error(" 文件IO读取异常，异常信息：{}", ex.getMessage());
            return null;
        }
    }
}
