package org.myframework.support.file.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.apache.commons.lang.ObjectUtils;
import org.myframework.commons.util.DateUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.support.file.config.VfsConfig.VfsPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <ol>
 * 单体模式获取vfs.xml配置信息
 * <li>{@link  }</li>
 *
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年7月15日
 *
 */
public class VfsXmlCofig {

	private static final Logger logger = LoggerFactory
			.getLogger(VfsXmlCofig.class);

	private static final VfsXmlCofig VFS_XML_COFIG = new VfsXmlCofig();

	private static final String CONFIG_FILE = "vfs.xml";

	public static VfsXmlCofig getInstance() {
		return VFS_XML_COFIG;
	}

	/**
	 * 从vfs.xml读取VFS系统的配置信息 配置信息包括：存储的根目录地址，不同文件类型如何存储到不同的目录，不同文件类型的文件大小要求等;
	 *
	 * @return
	 */
	public VfsConfig getVfsConfig() {
		VfsConfig vfsconfig = new VfsConfig();
		try {
			// do something with config
			HierarchicalConfiguration vfs = config.configurationAt("vfs");
			String baseStoreLocation = vfs.getString("baseStoreLocation");
			long defaultMaxSize = vfs.getLong("defaultMaxSize", Long.MAX_VALUE);
			vfsconfig.setBaseUrl(vfs.getString("baseUrl"));
			vfsconfig.setBaseStoreLocation(vfs.getString("baseStoreLocation"));
			vfsconfig.setDefaultPath(vfs.getString("defaultPath"));
			vfsconfig.setIllegalExt(vfs.getString("illegalExt"));
			vfsconfig.setAllowExt(vfs.getString("allowExt", "*.*"));
			vfsconfig.setDefaultMaxSize(defaultMaxSize);

			List<VfsPath> vfspaths = vfsconfig.getPaths();
			List<HierarchicalConfiguration> paths = config
					.configurationsAt("vfs.paths.path");
			for (HierarchicalConfiguration path : paths) {
				String relativeDir = path.getString("relativeDir");
				VfsPath vfsPath = new VfsPath();
				String fileExt = path.getString("fileExt");
				vfsPath.setFileExt(fileExt);
				vfsPath.setMaxSize(path.getLong("maxSize", defaultMaxSize));
				vfsPath.setRelativeDir(path.getString("relativeDir"));
				vfsPath.setAbsolutePath(baseStoreLocation + "/" + relativeDir);
				vfspaths.add(vfsPath);
			}
		} catch (Exception cex) {
			logger.error("读取配置文件信息失败" + cex.getMessage());
			return null;
		}
		return vfsconfig;
	}

	XMLConfiguration config;

	void setConfig(XMLConfiguration config) {
		this.config = config;
	}

	VfsXmlCofig() {
		try {
			logger.info("读取vfs.xml配置文件....." + CONFIG_FILE);
			XMLConfiguration config = new XMLConfiguration(CONFIG_FILE);
			ReloadingStrategy strategy = new FileChangedReloadingStrategy();
			config.setReloadingStrategy(strategy);
			setConfig(config);
		} catch (ConfigurationException e) {
			logger.error("初始化配置失败:" + e.getMessage());
		}
	}
	
	static final Map<String, String> staticParam = new  HashMap<String, String> (); 
	static {
		staticParam.put(DateUtils.FORMAT_DATE_YYYYMMDD, DateUtils.getCurrentDateAsString(DateUtils.FORMAT_DATE_YYYYMMDD) );
		staticParam.put(DateUtils.FORMAT_DATE_YYYYMMDDHH, DateUtils.getCurrentDateAsString(DateUtils.FORMAT_DATE_YYYYMMDDHH) );
		staticParam.put(DateUtils.FORMAT_DATE_YYYYMMDDHHMM, DateUtils.getCurrentDateAsString(DateUtils.FORMAT_DATE_YYYYMMDDHHMM) );
	}
	
	/**
	 * 获取动态配置的文件夹路径
	 * yyyyMMdd
	 * yyyyMMddHH
	 * yyyyMMddHHmm
	 * 
	 * @param origPath
	 * @param datamap
	 * @return
	 */
	public static String getDynamicPath(String origPath , Map<String, String> datamap ) {
		logger.debug("origPath:" + origPath);
		datamap.putAll(staticParam);
		logger.debug("datamap:" + datamap);
		if (datamap != null && !datamap.isEmpty()) {
			List<String> params = getReplaceParameterList(origPath);
			for (String param : params) {
				origPath = StringUtils.replaceOnce(origPath, "#" + param,
						ObjectUtils.toString(datamap.get(param)));
			}
		}
		return origPath ;
	}

	private static List<String> getReplaceParameterList(String origPath) {
		String regex = "\\#(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(origPath);
		List<String> params = new ArrayList<String>();
		while (matcher.find())
			params.add(matcher.group(1));
		return params;
	}
	
	
}
