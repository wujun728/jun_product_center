//package com.jun.plugin.module.qiniu.utils;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//import javax.security.auth.message.AuthException;
//
//import org.json.JSONException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.qiniu.common.QiniuException;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.util.Auth;
//
///**
// * 七牛云 手写工具类
// */
//public class QiniuUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);
//
//    PropertyUtil propertyUtil = new PropertyUtil();
//    private String bucketName = propertyUtil.getProperty("bucketName");
//    private String domain = propertyUtil.getProperty("domain");
//    private String ACCESS_KEY = propertyUtil.getProperty("ACCESS_KEY");
//    private String SECRET_KEY = propertyUtil.getProperty("SECRET_KEY");
//
//    //通过文件路径上传文件
//    public ExecuteResult<String> uploadFile(String localFile) throws AuthException, JSONException {
//        File file = new File(localFile);
//        /**
//         * 文件后缀名 文件扩展名
//         */
//        String filenameExtension = localFile.substring(localFile.lastIndexOf("."), localFile.length());
//        return uploadFile(file, filenameExtension);
//    }
//
//    //通过File上传
//    public ExecuteResult<String> uploadFile(File file, String filename) throws AuthException, JSONException {
//        ExecuteResult<String> executeResult = new ExecuteResult<String>();
////        String uptoken = getUpToken();
//        // 可选的上传选项，具体说明请参见使用手册。
////        PutExtra extra = new PutExtra();
////        PutRet ret = IoApi.putFile(uptoken,filename , extra);
//        com.qiniu.storage.Configuration cfg = new Configuration();
//	    UploadManager uploadManager = new UploadManager(cfg);
//	    Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
//	    String token = auth.uploadToken(bucketName);
//	    Response r;
//		try {
//			r = uploadManager.put(file, filename, token);
//			System.out.println(token);   //输出上传凭证
//			System.out.println(r.isOK());    //输出上传到七牛云之后的文件名称
//			System.out.println(r.toString());    //输出上传到七牛云之后的文件名称
//			if (r.isOK()) {
//				executeResult.setSuccessMessage("上传成功!");
//				executeResult.setResult(domain+r.url());
//			} else {
//				executeResult.addErrorMessage("上传失败");
//			}
//		} catch (QiniuException e) {
//			e.printStackTrace();
//		}
//
//
//        return executeResult;
//    }
//    public String getFileNameByDateAndUUID(String filenameExtension) {
//    	 SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd");
//         String filename = time.format(new Date()) + "/" + UUID.randomUUID() + filenameExtension;
//         return filename;
//
//    }
//
//    /**
//     * 从 inputstream 中写入七牛
//     *
//     * @param key     文件名
//     * @param content 要写入的内容
//     * @return
//     * @throws AuthException
//     * @throws JSONException
//     */
//    public boolean uploadFile(String content ,String fileName) throws AuthException, JSONException {
//        // 读取的时候按的二进制，所以这里要同一
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
//        // 可选的上传选项，具体说明请参见使用手册。
//        com.qiniu.storage.Configuration cfg = new Configuration();
//	    UploadManager uploadManager = new UploadManager(cfg);
//	    Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
//	    String token = auth.uploadToken(bucketName);
//
//	    Response r;
//		try {
//			r = uploadManager.put(QiniuUtil.chanageInputStream2byte(inputStream), fileName, token);
////	    	Response r = uploadManager.put(inputStream, key, token);
//			System.out.println(token);   //输出上传凭证
//			System.out.println(r.isOK());    //输出上传到七牛云之后的文件名称
//			System.out.println(r.toString());    //输出上传到七牛云之后的文件名称
//			if (r.isOK()) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (QiniuException e) {
//			e.printStackTrace();
//		}
//		return false;
//    }
//    public boolean uploadFile(InputStream ins,String fileName) throws AuthException, JSONException {
//    	com.qiniu.storage.Configuration cfg = new Configuration();
//    	UploadManager uploadManager = new UploadManager(cfg);
//    	Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
//    	String token = auth.uploadToken(bucketName);
//    	Response r;
//    	try {
//    		r = uploadManager.put(QiniuUtil.chanageInputStream2byte(ins), fileName, token);
//	    	//Response r = uploadManager.put(inputStream, key, token);
//    		System.out.println(token);   //输出上传凭证
//    		System.out.println(r.isOK());    //输出上传到七牛云之后的文件名称
//    		System.out.println(r.toString());    //输出上传到七牛云之后的文件名称
//    		if (r.isOK()) {
//    			return true;
//    		} else {
//    			return false;
//    		}
//    	} catch (QiniuException e) {
//    		e.printStackTrace();
//    	}
//    	return false;
//    }
//
//    public static byte[] chanageInputStream2byte(InputStream fis){
//        byte[] buffer = null;
//        try
//        {
////            FileInputStream fis = new FileInputStream(tradeFile);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int n;
//            while ((n = fis.read(b)) != -1)
//            {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return buffer;
//    }
//    public static byte[] File2byte(File tradeFile){
//    	byte[] buffer = null;
//    	try
//    	{
//            FileInputStream fis = new FileInputStream(tradeFile);
//    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//    		byte[] b = new byte[1024];
//    		int n;
//    		while ((n = fis.read(b)) != -1)
//    		{
//    			bos.write(b, 0, n);
//    		}
//    		fis.close();
//    		bos.close();
//    		buffer = bos.toByteArray();
//    	}catch (FileNotFoundException e){
//    		e.printStackTrace();
//    	}catch (IOException e){
//    		e.printStackTrace();
//    	}
//    	return buffer;
//    }
//
//    //获得下载地址
////    public String getDownloadFileUrl(String filename) throws Exception {
////        Mac mac = getMac();
////        String baseUrl = URLUtils.makeBaseUrl(domain, filename);
////        GetPolicy getPolicy = new GetPolicy();
////        String downloadUrl = getPolicy.makeRequest(baseUrl, mac);
////        return downloadUrl;
////    }
////
////    //删除文件
////    public void deleteFile(String filename) {
////        Mac mac = getMac();
////        RSClient client = new RSClient(mac);
////        client.delete(domain, filename);
////    }
////
////    //获取凭证
////    private String getUpToken() throws AuthException, JSONException {
////        Mac mac = getMac();
////        PutPolicy putPolicy = new PutPolicy(bucketName);
////        String uptoken = putPolicy.token(mac);
////        return uptoken;
////    }
////
////    private Mac getMac() {
////        Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
////        return mac;
////    }
//
//}