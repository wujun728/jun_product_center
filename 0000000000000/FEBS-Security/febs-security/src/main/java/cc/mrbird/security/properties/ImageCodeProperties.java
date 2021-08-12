package cc.mrbird.security.properties;

public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;

    private String createUrl = "";

    // 处理使用图形验证码认证 URL
    private String loginProcessingUrl = "";

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String getCreateUrl() {
        return createUrl;
    }

    @Override
    public void setCreateUrl(String createUrl) {
        this.createUrl = createUrl;
    }

    @Override
    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    @Override
    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }
}
