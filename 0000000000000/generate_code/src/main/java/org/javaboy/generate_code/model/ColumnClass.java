package org.javaboy.generate_code.model;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class ColumnClass {
    private String propertyName;
    private String columnName;
    private String type;
    private String remark;
    private Boolean isPrimary;

    @Override
    public String toString() {
        return "ColumnClass{" +
                "propertyName='" + propertyName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
