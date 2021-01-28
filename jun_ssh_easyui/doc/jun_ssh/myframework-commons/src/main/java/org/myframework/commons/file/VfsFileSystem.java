package org.myframework.commons.file;

import java.io.IOException;
import java.io.InputStream;

import org.myframework.commons.util.io.VfsUtils;

/**
 *
 * <ol>支持多种文件系统内的文件读取和写入 ，包括zip，hdfs,http,https,ftp,sftp等
 * <li>{@link http://commons.apache.org/proper/commons-vfs/api.html }</li>
 * <li>Supported File Systems @link http://commons.apache.org/proper/commons-vfs/filesystems.html#HDFS</li>
 * <li>vfs有自己的配置文件 providers.xml，见VFS.getManager() </li>
 * <li>This method will also automatically scan the classpath for a /META-INF/vfs-providers.xml file (also in jar files).
 *    If such a file is found Commons VFS uses it in addition to the default providers.xml. </li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月3日
 *
 */
public class VfsFileSystem implements FileSystem{

	@Override
	public void upload(InputStream inputStream, String fileUri)
			throws IOException {
		VfsUtils.copyFile(inputStream,  fileUri, true);
	}

	@Override
	public InputStream download(String fileUri) throws IOException {
		return VfsUtils.getInputStream(fileUri);
	}

}
