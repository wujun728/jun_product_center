package cc.mrbird.common.utils;

import cc.mrbird.common.properties.FebsProperies;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

/**
 * 七牛云Oss 工具类
 *
 * @author liuzx
 * @Date 2018年11月29日21:12:41
 */
@Component
public class QiniuOssUtil {

    private static Logger logger = LoggerFactory.getLogger(QiniuOssUtil.class);

    @Autowired
    private static FebsProperies properies;

    public static String upload(FileInputStream file, String fileName) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(properies.getOss().getQiniu().getAccessKey(), properies.getOss().getQiniu().getSecretKey());
            String upToken = auth.uploadToken(properies.getOss().getQiniu().getBucket());
            try {
                Response response = uploadManager.put(file, fileName, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                return properies.getOss().getQiniu().getPath() + "/" + putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                logger.error(r.toString());
                try {
                    logger.error(r.bodyString());
                } catch (QiniuException ignore) {

                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

}
