package com.zt.pojo;

import java.io.Serializable;

public class SysLogWithBLOBs extends SysLog implements Serializable {
    private String oldValue;

    private String newValue;

    private static final long serialVersionUID = 1L;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }
}