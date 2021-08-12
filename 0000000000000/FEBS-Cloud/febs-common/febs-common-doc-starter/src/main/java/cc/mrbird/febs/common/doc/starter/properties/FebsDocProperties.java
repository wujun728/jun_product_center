package cc.mrbird.febs.common.doc.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MrBird
 */
@ConfigurationProperties(prefix = "febs.doc")
public class FebsDocProperties {

    /**
     * 是否开启doc功能
     */
    private Boolean enable = true;
    /**
     * 接口扫描路径，如Controller路径
     */
    private String basePackage;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档描述颜色
     */
    private String descriptionColor = "#42b983";
    /**
     * 文档描述字体大小
     */
    private String descriptionFontSize = "14";
    /**
     * 服务url
     */
    private String termsOfServiceUrl;
    /**
     * 联系方式：姓名
     */
    private String name;
    /**
     * 联系方式：个人网站url
     */
    private String url;
    /**
     * 联系方式：邮箱
     */
    private String email;
    /**
     * 协议
     */
    private String license;
    /**
     * 协议地址
     */
    private String licenseUrl;
    /**
     * 版本
     */
    private String version;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(String descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public String getDescriptionFontSize() {
        return descriptionFontSize;
    }

    public void setDescriptionFontSize(String descriptionFontSize) {
        this.descriptionFontSize = descriptionFontSize;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "FebsDocProperties{" +
                "enable=" + enable +
                ", basePackage='" + basePackage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", descriptionColor='" + descriptionColor + '\'' +
                ", descriptionFontSize='" + descriptionFontSize + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", email='" + email + '\'' +
                ", license='" + license + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
