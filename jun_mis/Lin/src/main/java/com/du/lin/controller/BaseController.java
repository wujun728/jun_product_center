package com.du.lin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class BaseController{
	/**
	 * 下载文件
	 * @param response
	 * @param file
	 */
	protected void downloadFile(HttpServletResponse response , File file) {
		
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    try {
	      os = response.getOutputStream();
	      bis = new BufferedInputStream(new FileInputStream(file));
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
		
	}

}
