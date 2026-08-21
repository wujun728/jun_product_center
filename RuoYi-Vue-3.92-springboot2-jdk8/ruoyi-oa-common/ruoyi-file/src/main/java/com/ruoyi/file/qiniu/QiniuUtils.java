package com.ruoyi.file.qiniu;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.storage.BucketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiniuUtils {
    private static final Logger log = LoggerFactory.getLogger(QiniuUtils.class);

    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String domain;
    private long tokenTime = 3600 * 24 * 365 * 5;

    public QiniuUtils() {
        this.accessKey = System.getProperty("qiniu.accessKey", "");
        this.secretKey = System.getProperty("qiniu.secretKey", "");
        this.bucketName = System.getProperty("qiniu.bucketName", "qixing-files");
        this.domain = System.getProperty("qiniu.domain", "http://qiniu.hbqxcpa.cn");
    }

    public void uploadFile(String filePath, String fileName) throws QiniuException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(filePath, fileName, token);
        if (r.isOK()) {
            log.info("涓婁紶鎴愬姛! 璺緞: {}", domain + "/" + fileName);
        } else {
            log.info("涓婁紶澶辫触!");
        }
    }

    public void uploadFile(File file, String fileName) throws QiniuException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(file, fileName, token);
        if (r.isOK()) {
            log.info("涓婁紶鎴愬姛!");
        }
    }

    public void uploadFile(MultipartFile file, String fileName) throws QiniuException, IOException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(file.getBytes(), fileName, token);
        if (r.isOK()) {
            log.info("涓婁紶鎴愬姛!");
        }
    }

    public void uploadFile(InputStream inputStream, String fileName) throws QiniuException, IOException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(inputStream, fileName, token, null, null);
        if (r.isOK()) {
            log.info("涓婁紶鎴愬姛!");
        }
    }

    public void uploadFile(byte[] data, String fileName) throws QiniuException {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response r = uploadManager.put(data, fileName, token);
        if (r.isOK()) {
            log.info("涓婁紶鎴愬姛!");
        }
    }

    public void deleteFile(String fileName) throws QiniuException {
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(Auth.create(accessKey, secretKey), cfg);
        Response r = bucketManager.delete(bucketName, fileName);
        if (r.isOK()) {
            log.info("鍒犻櫎鎴愬姛!");
        }
    }

    public String getPrivateDownloadUrl(String fileName) {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(domain + "/" + fileName, tokenTime);
    }

    public static String generateFileName(String originalFilename) {
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + UUID.randomUUID().toString().substring(0, 8) + suffix;
    }
}