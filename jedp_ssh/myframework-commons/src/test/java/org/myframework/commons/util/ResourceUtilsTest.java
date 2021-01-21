package org.myframework.commons.util;

import java.net.URL;
import java.util.Enumeration;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;


public class ResourceUtilsTest {

	@Test
	public void testGetURL() throws Exception {
		ResourceUtils.getURL("classpath:config.properties");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> urls = loader.getResources("org/apache/commons/vfs2//Resources.properties");
		while (urls.hasMoreElements()) {
			URL fileUrl = urls.nextElement();
			PropertiesConfiguration config = new PropertiesConfiguration(fileUrl);
			String error = config.getString("vfs/create-manager.error");
			Assert.notNull(error);
		}
//		StringUtils.cleanPath(path)
//		ResourceUtils.getURL("file:/org/apache/commons/vfs2/Resources.properties"); 
	}

	@Test
	public void testExtractJarFileURL() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
