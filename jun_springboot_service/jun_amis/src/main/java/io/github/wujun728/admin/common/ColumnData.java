package io.github.wujun728.admin.common;

import java.util.HashMap;

public class ColumnData extends HashMap<String,Object> {
    public String getLabel(){
        return (String)super.get("label");
    }
    public String getName(){
        return (String)super.get("name");
    }

    public void setLabel(String label){
        super.put("label",label);
    }
    public void setName(String name){
        super.put("name",name);
    }
}
