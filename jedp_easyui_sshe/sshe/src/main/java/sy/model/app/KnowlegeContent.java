package sy.model.app;

import java.io.Serializable;

public class KnowlegeContent implements Serializable {
    private int contId;

    private int typeId;

    private byte contFormat;

    private String fileUrl;

    private String contLogo;

    private String content;

    private String templateUrl;

    private static final long serialVersionUID = 1L;

    public int getContId() {
        return contId;
    }

    public void setContId(int contId) {
        this.contId = contId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public byte getContFormat() {
        return contFormat;
    }

    public void setContFormat(byte contFormat) {
        this.contFormat = contFormat;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getContLogo() {
        return contLogo;
    }

    public void setContLogo(String contLogo) {
        this.contLogo = contLogo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }
}