package io.github.wujun728.admin.minio;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioTest {
    public static void main(String[] args) {
        try {
            // 使用MinIO服务的URL，访问密钥和秘密密钥初始化MinioClient对象
            MinioClient minioClient = new MinioClient("http://10.100.254.13:31814", "MeSiMJU6C5vYsBkJHgtA", "80OyyNLMfp4YjcYOfm5qxel5niSwHGiUwEKGK8DP");
//            MinioClient minioClient = new MinioClient("http://localhost:9000", "minioadmin", "minioadmin");

            // 检查bucket是否存在
            boolean isExist = minioClient.bucketExists("byk2kubevirt");
            if(isExist) {
                System.out.println("Bucket exists.");
            } else {
                System.out.println("Bucket does not exist.");
            }
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error: " + e);
        }
    }
}
