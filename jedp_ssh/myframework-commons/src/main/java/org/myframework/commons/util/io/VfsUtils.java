package org.myframework.commons.util.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.myframework.commons.util.ExceptionUtils;


/**
 *
 * <ol>VFS工具类封装，使用统一的接口进行文件操作（包括FTP,SFTP,本地文件系统）
 * <li>{@link VfsFileSystem }</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月3日
 *
 */
public class VfsUtils {

	private static FileSystemManager fsm = null;
	private static Log log = LogFactory.getLog(VfsUtils.class);
	static {
		try {
			fsm = VFS.getManager();
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * FileObject VFS文件通过PATH来获取
	 * @link http://commons.apache.org/proper/commons-vfs/filesystems.html#HDFS
	 * @see VfsFileSystemTest
	 * @param path
	 * @return
	 */
	public static FileObject getFile(String path) {
		try {
			FileSystemOptions options = new FileSystemOptions();
			//SFTP设置
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(options,
					false);
//			SftpFileSystemConfigBuilder.getInstance().setTimeout(options, 600);
			//ftp设置
			FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(options,
					false);
//			FtpFileSystemConfigBuilder.getInstance().setSoTimeout(options, 600);
			//
			return fsm.resolveFile(path, options);
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/**
	 * 文件删除
	 * @param path
	 */
	public static void delete(String path) {
		try {
			FileObject fo = getFile(path);
			fo.delete();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("文件删除失败");
		}
	}

	/**
	 * 判断是否文件夹
	 * @param path
	 * @return
	 */
	public static boolean isDirectory(String path) {
		try {
			FileObject fo = getFile(path);
			return fo.getType().equals(FileType.FOLDER);
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	/**
	 * 文件的文件流读取
	 * @param path 绝对地址
	 * @return
	 */
	public static InputStream getInputStream(String path) {
		try {
			FileObject fo = getFile(path);
			return fo.getContent().getInputStream();
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	/**
	 * 文件的流写入
	 * @param path
	 * @return
	 */
	public static OutputStream getOutputStream(String path) {
		try {
			FileObject fo = getFile(path);
			return fo.getContent().getOutputStream();
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	/**
	 * 判断是否文件
	 * @param path
	 * @return
	 */
	public static boolean isFile(String path) {
		try {
			FileObject fo = getFile(path);
			return fo.getType().equals(FileType.FILE);
		} catch (FileSystemException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	/**
	 * 函数描述：根据传入的文件路径创建文件夹(包括各级父文件夹)。如果路径中有文件，会自动去掉文件名。 （文件的判断是
	 * 以最后一个"/"之后是否有"."为标识的，）
	 *
	 * @param path
	 * @return 如果创建成功，返回true；否则，返回false;
	 */
	public static boolean mkdirs(String path) {
		String realPath = "";
		path = path.replaceAll("\\\\", "/");
		// 如果该路径已"/"结尾，则整个字符串都是路径
		if (path.endsWith("/")) {
			realPath = path;
		} else {
			int fileNamePoint = path.lastIndexOf("/");
			// 获取真正的路径
			if (fileNamePoint >= 0) {
				realPath = path.substring(0, fileNamePoint);
			}
			// 读取文件名
			String fileName = path.substring(fileNamePoint + 1);
			// 如果读取的文件名中没有"."，说明整个字符串都是路径
			if (fileName.indexOf(".") < 0) {
				realPath = path;
			}
		}
		try {
			FileObject fo = getFile(realPath);
			fo.createFolder();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 函数描述：对文件进行copy
	 *
	 * @param sourceFilePath
	 *            源文件的全部路径+文件名
	 * @param targetFilePath
	 *            目标文件的全部路径+文件名
	 * @param overWrite
	 *            如果目标文件存在，是否覆盖。true:覆盖；false:不覆盖(当源文件和目标文件都存在并且不覆盖时,返回true)。
	 * @return true:成功；false:失败; (当源文件和目标文件都存在并且不覆盖时,返回true)。
	 */
	public static boolean copyFile(String sourceFilePath,
			String targetFilePath, boolean overWrite) throws IOException {
		if (StringUtils.isBlank(sourceFilePath)
				|| StringUtils.isBlank(targetFilePath)) {
			throw new IOException("源文件或者目标文件为空");
		}
		FileObject from = getFile(sourceFilePath);
		FileObject to = getFile(targetFilePath);
		if (to.exists()) {
			if (to.getType() == FileType.FILE) {
				if (overWrite && !to.delete()) {
					throw new IOException("目标文件[" + targetFilePath
							+ "]被保护，不能被覆盖！");
				} else if (!overWrite) {
					throw new IOException("目标文件[" + targetFilePath + "]已经存在！");
				}
			}
		}
		to.copyFrom(from, Selectors.SELECT_ALL);
		return true;
	}

	/**
	 * @param inputStream WEB表单上传时的二进制文件流
	 * @param webFileName WEB表单上传时的二进制文件的文件名
	 * @param targetFilePath 上传的目标位置
	 * @param overWrite
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(InputStream inputStream,
			String targetFilePath, boolean overWrite) throws IOException{
		String fileExt = FileUtils.getExtension(targetFilePath, "");
		File tmpFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString() , fileExt) ;
		return copyFile(tmpFile.getAbsolutePath(),targetFilePath,overWrite);
	}

	/**
	 * Moving a File to Another File ,没有进行磁盘空间大小的判断
	 *
	 * @param srcFile
	 *            源文件 eg: c:\windows\abc.txt
	 * @param targetFile
	 *            目标文件 eg: c:\temp\abc.txt
	 * @param overwrite
	 *            如果目标文件存在，会删除；不同于copyTo
	 * @return success
	 */
	public static boolean moveFile(String srcFile, String targetFile,
			boolean overWrite) throws IOException {
		if (srcFile.equals(targetFile)) {
			return true;
		}
		FileObject src = getFile(srcFile);
		// File (or directory) to be moved
		if (StringUtils.isNotBlank(srcFile) && !src.exists()) {
			throw new IOException("源文件[" + srcFile + "]不存在");
		}
		// Destination directory
		FileObject to = getFile(targetFile);
		if (to.exists()) {
			if (to.getType() == FileType.FILE) {
				if (overWrite && !to.delete()) {
					throw new IOException("目标文件[" + targetFile + "]被保护，不能被覆盖！");
				} else if (!overWrite) {
					throw new IOException("目标文件[" + targetFile + "]已经存在！");
				}
			}
		}
		src.moveTo(to);
		return true;
	}

}
