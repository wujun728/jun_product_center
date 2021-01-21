package org.myframework.commons.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;


public class LocalFileSystem implements FileSystem {

	@Override
	public void upload(InputStream inputStream,String fileUri) throws IOException {
		File file = new File(fileUri);
		FileUtils.copyInputStreamToFile(inputStream, file);
	}

	@Override
	public InputStream download(String fileUri)  throws IOException{
		File file = new File(fileUri);
		return  new FileInputStream(file);
	}

}
