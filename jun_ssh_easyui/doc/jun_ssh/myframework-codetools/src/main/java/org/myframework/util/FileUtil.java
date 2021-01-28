package org.myframework.util;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.util.StringUtils;


public class FileUtil {
	private static final Log log = LogFactory.getLog(FileUtil.class);
	public static String FILE ="FILE_" ;
	/** 
	 * @param filePath 
	 */
	public static void createDir(String filePath) {
		if (!new File(filePath).mkdirs())
			log.error("createDir  fail");
		else
			log.warn("createDir  success");
	}

	/**
	 * @param file
	 * @throws Exception
	 */
	public static void createFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			log.debug("file WILL BE REWRITE !!! ");
			log.debug("file PATH :" + f.getAbsolutePath());
			log.debug("file SIZE:" + f.length());
		} else {
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	/**
	 * @param className  
	 * @return
	 */
	public static String getJavaFileName(String className) {
		log.debug("className :" +className);
		String javaFileName = StringUtils.getPackageAsPath(getPackage(className)) +getClassName(className)+ ".java";
		return javaFileName;
	}
	
	/**
	 * @param className
	 * @return
	 */
	public static String getPackage(String className) {
		return className.substring(0, className.lastIndexOf("."));
	}
	
	/**
	 * @param className
	 * @return
	 */
	public static String getClassName(String className) {
		return className.substring( className.lastIndexOf(".")+1);
	}
	
	/**
	 * @param f
	 * @return
	 */
	public static String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    /**
     * @param filename
     * @return
     */
    public static String getExtension(String filename) {
        return getExtension(filename, "");
    }

    /**
     * @param filename
     * @param defExt
     * @return
     */
    public static String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i >-1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

    /**
     * @param filename
     * @return
     */
    public static String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i >-1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }
        return filename;
    }
    
	/**
	 * @param fileName
	 * @return
	 */
    public static String renameFile(String prefix,String fileName) {
    	StringBuffer newFileName = new StringBuffer(prefix!=null?prefix:FILE);
		newFileName.append(DateUtil.format(new Date(), "yyyyMMddHHmmssms"))
				.append(".").append(getExtension(fileName));
		return newFileName.toString();
	}
    
    /**
	 * @param fileName
	 * @return
	 */
    public static String renameFile( String fileName) {
		return renameFile(null,fileName);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String renameFile(String prefix,File file) {
		StringBuffer newFileName = new StringBuffer(prefix!=null?prefix:FILE);
		newFileName.append(DateUtil.format(new Date(), "yyyyMMddHHmmssms"))
				.append(".").append(getExtension(file));
		return newFileName.toString();
	}


	public static void main(String[] args) throws Exception {
		String className = "ims.tools.web.action.taskAction";
		String fileName = FileUtil.getJavaFileName(className);
		log.debug(DateUtil.getNowTime());
		log.debug(renameFile("abc.txt.xls"));
//		System.out.println(fileName);
//		FileUtils.createFile("D:/"+ fileName);
	}
	
	
}
