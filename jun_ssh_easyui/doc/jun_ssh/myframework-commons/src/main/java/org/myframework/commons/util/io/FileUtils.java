package org.myframework.commons.util.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.myframework.commons.util.StringUtils;

public class FileUtils extends org.apache.commons.io.FileUtils {

	public FileUtils() {
	}

	public static void newFile(String path) throws IOException {
		(new File(path)).createNewFile();
	}

	public static void newFile(String path, String content) throws IOException {
		File myfile = new File(path);
		if (!myfile.exists())
			myfile.createNewFile();
		FileWriter fw = new FileWriter(myfile);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		fw.close();
	}

	public static void writeFile(String filePath, String content,
			boolean append) throws IOException {
		File myfile = new File(filePath);
		if (!append) {
			myfile.delete();
			myfile.createNewFile();
		}
		FileWriter fw = new FileWriter(filePath, true);
		fw.write(content);
		fw.close();
	}

	public static String readFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		File myfile = new File(path);
		int fileLen = (int) myfile.length();
		char buf[] = new char[fileLen];
		int len = fr.read(buf);
		String msg = new String(buf, 0, len);
		fr.close();
		return msg;
	}

	public static void delFile(String path) throws IOException {
		File myfile = new File(path);
		if (!myfile.isDirectory())
			myfile.delete();
	}

	public static List readFileByLine(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		List list = new ArrayList();
		for (; br.readLine() != null; list.add(br.readLine()))
			;
		br.close();
		fr.close();
		return list;
	}

	public static String readFileByUTF8(String path) throws IOException {
		String content = "";
		File f = new File(path);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),
				"UTF-8");
		BufferedReader reader = new BufferedReader(read);
		String line;
		while ((line = reader.readLine()) != null)
			content = (new StringBuilder(String.valueOf(content))).append(line)
					.append("\n").toString();
		return content;
	}

	public static void saveFileByUTF8(String path, String content)
			throws IOException {
		File myfile = new File(path);
		myfile.delete();
		myfile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myfile.getAbsoluteFile());
		Writer w = new OutputStreamWriter(fos, "UTF-8");
		w.write(content);
		w.close();
		fos.close();
	}

	public static void newFolder(String path) throws IOException {
		File mydir = new File(path);
		if (!mydir.exists())
			mydir.mkdirs();
	}

	public static InputStream string2stream(String srcStr) {
		InputStream is = null;
		if (!srcStr.trim().equals("")) {
			String str = srcStr;
			is = new ByteArrayInputStream(str.getBytes());
		}
		return is;
	}

	public static String stream2string(InputStream srcStream)
			throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(srcStream));
		StringBuffer buf = new StringBuffer();
		for (String line = ""; (line = br.readLine()) != null;)
			buf.append((new StringBuilder(String.valueOf(line))).append("\n")
					.toString());

		return buf.toString();
	}

	public static InputStream getStreamInFile(String path) throws IOException {
		File myfile = new File(path);
		InputStream is = new FileInputStream(myfile);
		return is;
	}

	/**
	 * 创建临时文件
	 *
	 * @param inputStream
	 * @param name
	 *            文件名
	 * @param ext
	 *            扩展名
	 * @return
	 * @throws IOException
	 */
	public static File createTmpFile(InputStream inputStream, String name,
			String ext) throws IOException {
		FileOutputStream fos = null;
		try {
			File tmpFile = File.createTempFile(name, '.' + ext);
			tmpFile.deleteOnExit();
			fos = new FileOutputStream(tmpFile);
			int read = 0;
			byte[] bytes = new byte[1024 * 100];
			while ((read = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			fos.flush();
			return tmpFile;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * UUID重命名文件名，避免中文问题及磁盘文件重名等问题
	 *
	 * @param originalFilename
	 * @return
	 */
	public static String newFileName(String originalFilename) {
		String ext = getExtension(originalFilename, "");
		return UUID.randomUUID().toString() + "." + ext;
	}

	/**
	 * @param filename
	 * @param defExt
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
		String filenameExtension = StringUtils.getFilenameExtension(filename);
		return StringUtils.hasLength(filenameExtension) ? filenameExtension
				: defExt;
	}

	/**
	 * 字节描述
	 * 
	 * @param size
	 * @return
	 */
	public static String byteCountToDisplaySize(long size) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.0");
		double size0 = 1.0 * size;
		String displaySize;
		if (size / ONE_GB > 0L)
			displaySize = df.format(size0 / ONE_GB) + " GB";
		else if (size / ONE_MB > 0L)
			displaySize = df.format(size0 / ONE_MB) + " MB";
		else if (size / ONE_KB > 0L)
			displaySize = df.format(size0 / ONE_KB) + " KB";
		else {
			displaySize = size + " bytes";
		}
		return displaySize;
	}
}
