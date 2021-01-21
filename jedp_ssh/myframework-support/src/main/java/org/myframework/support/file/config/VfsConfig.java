package org.myframework.support.file.config;

import java.util.ArrayList;
import java.util.List;


public class VfsConfig {

	public VfsConfig() {

	}

	private String baseUrl ;

	private String baseStoreLocation;

	private String defaultPath;

	private String illegalExt;

	private String allowExt;

	private Long defaultMaxSize;

	private List<VfsPath> paths = new ArrayList<VfsPath>();

	public Long getDefaultMaxSize() {
		return defaultMaxSize;
	}

	public void setDefaultMaxSize(Long defaultMaxSize) {
		this.defaultMaxSize = defaultMaxSize;
	}

	public String getIllegalExt() {
		return illegalExt;
	}

	public void setIllegalExt(String illegalExt) {
		this.illegalExt = illegalExt;
	}

	public String getAllowExt() {
		return allowExt;
	}

	public void setAllowExt(String allowExt) {
		this.allowExt = allowExt;
	}

	public String getDefaultPath() {
		return defaultPath;
	}

	public void setDefaultPath(String defaultPath) {
		this.defaultPath = defaultPath;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseStoreLocation() {
		return baseStoreLocation;
	}

	public void setBaseStoreLocation(String baseStoreLocation) {
		this.baseStoreLocation = baseStoreLocation;
	}

	public List<VfsPath> getPaths() {
		return paths;
	}

	public void setPaths(List<VfsPath> paths) {
		this.paths = paths;
	}

	public static class VfsPath {
		private String relativeDir;
		private String fileExt ;
		private String absolutePath ;
		private long maxSize ;

		public String getAbsolutePath() {
			return absolutePath;
		}
		public void setAbsolutePath(String absolutePath) {
			this.absolutePath = absolutePath;
		}
		public String getRelativeDir() {
			return relativeDir;
		}
		public void setRelativeDir(String relativeDir) {
			this.relativeDir = relativeDir;
		}
		public String getFileExt() {
			return fileExt;
		}
		public void setFileExt(String fileExt) {
			this.fileExt = fileExt;
		}

		public long getMaxSize() {
			return maxSize;
		}
		public void setMaxSize(long maxSize) {
			this.maxSize = maxSize;
		}
		@Override
		public String toString() {
			return "VfsPath [relativeDir=" + relativeDir + ", fileExt="
					+ fileExt + ", absolutePath=" + absolutePath + "]";
		}

	}

	@Override
	public String toString() {
		return "VfsConfig [baseUrl=" + baseUrl + ", baseStoreLocation="
				+ baseStoreLocation + ", paths=" + paths + "]";
	}

}
