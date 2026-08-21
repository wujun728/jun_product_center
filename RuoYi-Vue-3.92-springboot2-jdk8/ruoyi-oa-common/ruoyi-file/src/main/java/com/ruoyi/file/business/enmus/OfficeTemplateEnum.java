package com.ruoyi.file.business.enmus;

/**
 * office模板枚举
 * @Author wocurr.com
 */
public enum OfficeTemplateEnum {
    WORD("docx", "template/Word.docx"),
    EXCEL("xlsx", "template/Excel.xlsx"),
    PPT("pptx", "template/PowerPoint.pptx");
    private String extendName;
    private String templatePath;
    OfficeTemplateEnum(String extendName, String templatePath) {
        this.extendName = extendName;
        this.templatePath = templatePath;
    }
    public String getExtendName() {
        return extendName;
    }
    public String getTemplatePath() {
        return templatePath;
    }
    public static OfficeTemplateEnum getEnum(String extendName) {
        for (OfficeTemplateEnum officeTemplateEnum : values()) {
            if (officeTemplateEnum.getExtendName().equals(extendName)) {
                return officeTemplateEnum;
            }
        }
        return null;
    }
}
