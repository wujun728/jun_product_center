package com.jun.plugin.file.common.comfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 文件上传参数配置类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String path;
    private String url;
    private String accessUrl;
    
    private String qiniuAccessKey;
    private String qiniuBucketName;
    private String qiniuDomain;
    private String qiniuPrefix;
    private String qiniuSecretKey;
    private String type;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

        //set accessUrl
        if (StringUtils.isEmpty(url)) {
            this.accessUrl = null;
        }
        this.accessUrl = url.substring(url.lastIndexOf("/")) + "/**";
        System.out.println("accessUrl=" + accessUrl);
    }

    public String getAccessUrl() {
        return accessUrl;
    }

	public String getQiniuAccessKey() {
		return qiniuAccessKey;
	}

	public void setQiniuAccessKey(String qiniuAccessKey) {
		this.qiniuAccessKey = qiniuAccessKey;
	}

	public String getQiniuBucketName() {
		return qiniuBucketName;
	}

	public void setQiniuBucketName(String qiniuBucketName) {
		this.qiniuBucketName = qiniuBucketName;
	}

	public String getQiniuDomain() {
		return qiniuDomain;
	}

	public void setQiniuDomain(String qiniuDomain) {
		this.qiniuDomain = qiniuDomain;
	}

	public String getQiniuPrefix() {
		return qiniuPrefix;
	}

	public void setQiniuPrefix(String qiniuPrefix) {
		this.qiniuPrefix = qiniuPrefix;
	}

	public String getQiniuSecretKey() {
		return qiniuSecretKey;
	}

	public void setQiniuSecretKey(String qiniuSecretKey) {
		this.qiniuSecretKey = qiniuSecretKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
    
    

}