package org.myframework.commons.file;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * <ol>实现WEB应用的文件上传下载功能
 * <li>{@link  }</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月3日
 *
 */
public interface FileSystem {

	/**
	 * @param inputStream WEB表单的file
	 * @param fileUri 上传的目录,是绝对地址(可以是WEB应用所在磁盘，FTP,SFTP等)
	 * @throws IOException
	 */
	public void upload(InputStream inputStream,String fileUri) throws IOException;

	/**
	 * @param fileUri 文件存储绝对地址(可以是WEB应用所在磁盘，FTP,SFTP等)
	 * @return
	 * @throws IOException
	 */
	public InputStream download(String fileUri ) throws IOException ;

}
