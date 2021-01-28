package com.zt.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JsonData {
    private boolean result; //操作结果
    private int status; //0正常  1失败
    private String msg;
    private Object data;

    private JsonData(int status,boolean result){
        this.status = status;
        this.result = result;
    }

    public static JsonData success(){
        return new JsonData(0,true);
    }

    public static JsonData success(Object data){
        JsonData jsonData = new JsonData(0,true);
        jsonData.setData(data);
        return jsonData;
    }

    public static JsonData success(String msg){
        JsonData jsonData = new JsonData(0,true);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(1,false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String ,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("status",this.status);
        map.put("msg",this.msg);
        map.put("data",this.data);
        return map;
    }

}
