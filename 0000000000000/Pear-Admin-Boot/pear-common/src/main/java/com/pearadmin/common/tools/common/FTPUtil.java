package com.pearadmin.common.tools.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private FTPUtil() {
    }

    public static FTPClient open(String hostname, String username, String password){
        return open(hostname, 21, username, password, "UTF-8");
    }

    public static FTPClient open(String hostname, int port, String username, String password, String controlEncoding) {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding(controlEncoding);
        ftpClient.setDataTimeout(60000);
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
            } else {
                logger.error("Failed to login the FTP server. Please check your username and password");
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    public static void close(FTPClient ftpClient) {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            logger.error("unknow failed.{}", e.getMessage());
        }
    }

    public static void upload(FTPClient ftpClient, String path, String fileName, InputStream is) {
        try {
            if (!ftpClient.changeWorkingDirectory(path)) {
                mkdirs(ftpClient, path);
            }
            ftpClient.storeFile("/" + path + "/" + fileName, is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(FTPClient ftpClient, String path, OutputStream os){
        try {
            ftpClient.retrieveFile(path, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(FTPClient ftpClient, String path){
        try{
            ftpClient.deleteFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mkdirs(FTPClient ftpClient, String path) {
        path = path.replace('\\', '/');
        StringBuffer sb = new StringBuffer();
        String[] dirs = path.split("/");
        try {
            for (int i = 0; i < dirs.length; i++) {
                if (!"".equals(dirs[i])) {
                    sb.append(dirs[i] + "/");
                    ftpClient.makeDirectory(sb.toString());
                }
            }
        }catch (Exception e){
            logger.error("please check your path. {}", e.getMessage());
        }
    }
}
