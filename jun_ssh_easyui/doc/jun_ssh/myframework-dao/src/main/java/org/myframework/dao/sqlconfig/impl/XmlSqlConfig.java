package org.myframework.dao.sqlconfig.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Node;
import org.myframework.commons.util.StringUtils;
import org.myframework.commons.util.XmlUtils;
import org.myframework.dao.exception.SqlConfigFileLoadException;
import org.myframework.dao.sqlconfig.SqlMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ResourceUtils;

/**
 * desc :
 * <ol>
 * <li>{@link  }
 *
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public class XmlSqlConfig extends BaseSqlConfig implements InitializingBean {

	protected final Log log = LogFactory.getLog(getClass());

	String mapperDir = "sqlvelocity";

	public void setMapperDir(String mapperDir) {
		this.mapperDir = mapperDir;
	}

	public XmlSqlConfig(){
		//loadConfig();
	}

	/**
	 *
	 */
	private void loadConfig() {
		mapperDir  = mapperDir.startsWith("classpath:") ? mapperDir : "classpath:"+mapperDir;
		File file =null;
		try {
			file = ResourceUtils.getFile(mapperDir);
		} catch (FileNotFoundException e) {
			log.error(mapperDir + ">>>"+ e.getMessage());
			return ;
		}
		try {
			file = ResourceUtils.getFile(mapperDir);
			log.debug("load sqlconfig from dirctory :"+file.getPath());
			File files[] = file.listFiles();
			for (int i = 0; files!=null && i < files.length; i++) {
				reload(files[i].getName());
			}
		} catch (FileNotFoundException e) {
			throw new SqlConfigFileLoadException(" sqlconfig file not find !! ", e );
		}
	}

	/*
	 * @param dist 文件名
	 * 重载 mapperDir+ "/" + dist 对应的文件内容
	 */
	public void reload(String dist) {
		try {
			File file = ResourceUtils.getFile(mapperDir+ "/" + dist);
			final String filePath  = file.getAbsolutePath() ;
			log.debug("load sqlconfig from file:" + filePath);
			InputStream input = new FileInputStream(file);
			Document docServiceCfg = XmlUtils.fromXML(input, null);
			List<Node> nodes = docServiceCfg.selectNodes("//mapper/sql");
			final Node root = docServiceCfg.selectSingleNode("//mapper");
			final String  namespace = root.valueOf("@namespace");
			final String  globalUseCache = root.valueOf("@useCache");
			for (final Node node : nodes) {
				SqlMapper sqlMapper =  new SqlMapper (){
					public boolean isUseCache() {
						String nodeUseCache = node.valueOf("@useCache");
						if (!StringUtils.isEmpty(nodeUseCache)) {
							return "true".equalsIgnoreCase(nodeUseCache);
						} else {
							return "true".equalsIgnoreCase(globalUseCache);
						}
					}

					public boolean isFlushCache() {
						return "TRUE".equalsIgnoreCase(node.valueOf("@flushCache"));
					}

					public String getSqlKey() {
						return namespace + "." + node.valueOf("@id");
					}

					public String getSqlCode() {
						return node.getText();
					}
					@Override
					public String toString() {
						return  " SqlMapper  [isUseCache()=" + isUseCache() + ", getSqlKey()="
								+ getSqlKey() + ", getSqlCode()=" + getSqlCode()
								+ ", isFlushCache()=" + isFlushCache() + " ,filePath = "+filePath+"]";
					}

				} ;
				this.removeSqlMapper(sqlMapper.getSqlKey());
				this.addSqlMapper(sqlMapper.getSqlKey(), sqlMapper);
			}
		} catch (Exception e) {
			throw new SqlConfigFileLoadException(" sqlconfig file load fail ", e );
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (mapperDir == null) {
			throw new IllegalArgumentException("Property 'mapperDir' is required");
		}
		loadConfig();
	}

}
