package com.shuogesha.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 阿里云存储
 * @author zhaohaiyuan
 *
 */
public class AliOSSUpload {
		private static String endpoint = "oss-cn-chengdu.aliyuncs.com";
	    private static String accessKeyId = "LTAI4FbdGLw46ZPdHtZCLZZW";
	    private static String accessKeySecret = "RW4wPe5vKIEfSH4j3bf71dH4sejW39";
	    private static String bucketName = "tripscheme"; 
	    private static String endpoint1 = "";//我们的自己服务器
	    
	    static {
	        InputStream inputStream = AliOSSUpload.class.getClassLoader().getResourceAsStream("oss.properties");
	        Properties prop = new Properties();
	        try {
	            prop.load(inputStream);
	        } catch (IOException e) {
	            
	        }
	        endpoint = prop.getProperty("endpoint");
	        accessKeyId = prop.getProperty("accessKeyId"); 
	        accessKeySecret = prop.getProperty("accessKeySecret"); 
	        bucketName = prop.getProperty("bucketName"); 
	        endpoint1 = prop.getProperty("endpoint1"); 
	    }
	    
	    
	    public static OSS generateOssClient() {
	        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	        return ossClient;
	    }
	 
	    public static void uploadFile(OSS ossClient, File file, String key) {
	        try {
	            FileInputStream is = new FileInputStream(file);
	            String e = file.getName();
	            Long fileSize = Long.valueOf(file.length());
	            ObjectMetadata metadata = new ObjectMetadata();
	            metadata.setContentLength((long)is.available());
	            metadata.setCacheControl("no-cache");
	            metadata.setHeader("Pragma", "no-cache");
	            metadata.setContentEncoding("utf-8");
	            String type=contentType(e.substring(e.lastIndexOf(".")+1));
	            metadata.setContentType(type);
	            //除了图片、文件、apk等。mp4的要求在线播放
	            if(type.equals("video/mp4")){
	            	metadata.setContentDisposition("inline;filename=" + e);//表示能在线播放
	            }else{
	            	metadata.setContentDisposition("filename/filesize=" + e + "/" + fileSize + "Byte.");
	            } 
	            PutObjectResult putResult = ossClient.putObject(bucketName, key, is, metadata);
	            String resultStr = putResult.getETag();
	            System.out.println("resultStr:" + resultStr);
	        } catch (FileNotFoundException var9) {
	            var9.printStackTrace();
	        } catch (IOException var10) {
	            var10.printStackTrace();
	        }
	 
	    }
	 
	    public static void uploadFileInputStream(OSS ossClient, InputStream is, String fileName, Long fileSize, String key) {
	        try {
	            ObjectMetadata e = new ObjectMetadata();
	            e.setContentLength((long)is.available());
	            e.setCacheControl("no-cache");
	            e.setHeader("Pragma", "no-cache");
	            e.setContentEncoding("utf-8");
	            
	            String type=contentType(fileName.substring(fileName.lastIndexOf(".")+1));
	            e.setContentType(type);
	            //除了图片、文件、apk等。mp4的要求在线播放
	            if(type.equals("video/mp4")){
	            	e.setContentDisposition("inline;filename=" + fileName);//表示能在线播放
	            }else{
	            	 e.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
	            }  
 	            PutObjectResult putResult = ossClient.putObject(bucketName, key, is, e);
	            String resultStr = putResult.getETag();
	            System.out.println("resultStr:" + resultStr);
	        } catch (FileNotFoundException var8) {
	            var8.printStackTrace();
	        } catch (IOException var9) {
	            var9.printStackTrace();
	        }
	 
	    }
	    /**
	     * 下载文件
	     * @param client
	     * @param bucketName
	     * @param key
	     * @param filename
	     * @throws OSSException
	     * @throws ClientException
	     */
	    private static void downloadFile(OSS client, String bucketName, String key, String filename) throws OSSException, ClientException {
	        client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
	    }
	    /**
	     * 删除文件
	     * @param client
	     * @param bucketName
	     * @param key
	     * @param filename
	     * @throws OSSException
	     * @throws ClientException
	     */
	    private static void deleteFile(OSS client, String key) throws OSSException, ClientException {
	    	client.deleteObject(new GetObjectRequest(bucketName, key));
  	    }
	    /**
	     * 生产预览链接
	     * @param ossClient
	     * @param key
	     * @return
	     */
	    public static String generateFileUrl(OSS ossClient, String key) {
	    	//有效期10年
//	        Date expiration = new Date((new Date()).getTime() + 3600l * 1000 * 24 * 365 * 10);
//	        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
	    	String url="http://"+bucketName + "." + endpoint+"/"+key;
	        //如果没有配置自己的域名
	        if(StringUtils.isBlank(endpoint1)){
	        	  return url.toString();
	        } 
//	        System.out.println(url.toString().replaceAll("https://"+bucketName + "." + endpoint, endpoint1)
	        return url.toString().replaceAll("http://"+bucketName + "." + endpoint, endpoint1);
	    }
	    
	    /**
	     * 判断OSS服务文件上传时文件的contentType
	     * @param FilenameExtension
	     * @return
	     */
	    public static String contentType(String FilenameExtension){
	        if(FilenameExtension.equals("BMP")||FilenameExtension.equals("bmp")){return "image/bmp";}
	        if(FilenameExtension.equals("GIF")||FilenameExtension.equals("gif")){return "image/gif";}
	        if(FilenameExtension.equals("JPEG")||FilenameExtension.equals("jpeg")||
	           FilenameExtension.equals("JPG")||FilenameExtension.equals("jpg")||  
	           FilenameExtension.equals("PNG")||FilenameExtension.equals("png")){return "image/jpeg";}
	        if(FilenameExtension.equals("HTML")||FilenameExtension.equals("html")){return "text/html";}
	        if(FilenameExtension.equals("TXT")||FilenameExtension.equals("txt")){return "text/plain";}
	        if(FilenameExtension.equals("VSD")||FilenameExtension.equals("vsd")){return "application/vnd.visio";}
	        if(FilenameExtension.equals("PPTX")||FilenameExtension.equals("pptx")||
	           FilenameExtension.equals("PPT")||FilenameExtension.equals("ppt")){return "application/vnd.ms-powerpoint";}
	        if(FilenameExtension.equals("DOCX")||FilenameExtension.equals("docx")||
	           FilenameExtension.equals("DOC")||FilenameExtension.equals("doc")){return "application/msword";}
	        if(FilenameExtension.equals("XML")||FilenameExtension.equals("xml")){return "text/xml";}
	        if(FilenameExtension.equals("MP4")||FilenameExtension.equals("mp4")){
	           return "video/mp4";
	        }
	        return "text/html";
	      }
	 
	    
	    /**
	     * 上传图片or视频
	     * @param key
	     * @param file
	     * @throws IOException
	     */
	    public static String upload(String key,MultipartFile file) throws IOException {        
 	        OSS ossClient = generateOssClient();
	        try { 
	        	uploadFileInputStream(ossClient, file.getInputStream(), file.getOriginalFilename(), file.getSize(), key);
	            //生成文件可访问的url
	            String url = generateFileUrl(ossClient,key);
	            //关闭ossclient客户端
	            ossClient.shutdown(); 
	            System.out.println("oss-url:" + url);
	            return url;
	            
	        } catch (OSSException oe) {
	            System.out.println("Caught an OSSException, which means your request made it to OSS, "
	                    + "but was rejected with an error response for some reason.");
	            System.out.println("Error Message: " + oe.getErrorMessage());
	            System.out.println("Error Code:       " + oe.getErrorCode());
	            System.out.println("Request ID:      " + oe.getRequestId());
	            System.out.println("Host ID:           " + oe.getHostId());
	        } catch (ClientException ce) {
	            System.out.println("Caught an ClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with OSS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ce.getMessage());
	        } catch (Throwable e) {
	            e.printStackTrace();
	        } finally {
	            ossClient.shutdown();
	        }
	        
	        return "";
	}
	/**
	 * 删除云存储的图片  
	 * @param key
	 * @throws IOException
	 */
	public static void delete(String key) throws IOException {        
			String fileUrl="";
			if(StringUtils.isBlank(endpoint1)){//如果自定义
				fileUrl=key.replaceAll("http://"+bucketName + "." + endpoint+"/", "");
			}else{
				fileUrl=key.replaceAll(endpoint1, "");
			} 
			
			if(StringUtils.isBlank(fileUrl)){
				return ;
			} 
			OSS ossClient = generateOssClient();
	        try {   
	            //生成文件可访问的url
	            String url = generateFileUrl(ossClient,fileUrl);
	            deleteFile(ossClient, fileUrl);
	            //关闭ossclient客户端
	            ossClient.shutdown(); 
	            System.out.println("oss-url:" + url); 
	        } catch (OSSException oe) {
	            System.out.println("Caught an OSSException, which means your request made it to OSS, "
	                    + "but was rejected with an error response for some reason.");
	            System.out.println("Error Message: " + oe.getErrorMessage());
	            System.out.println("Error Code:       " + oe.getErrorCode());
	            System.out.println("Request ID:      " + oe.getRequestId());
	            System.out.println("Host ID:           " + oe.getHostId());
	        } catch (ClientException ce) {
	            System.out.println("Caught an ClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with OSS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ce.getMessage());
	        } catch (Throwable e) {
	            //e.printStackTrace();
	        } finally {
	            ossClient.shutdown();
	        }
	        
 	}
	 

}
