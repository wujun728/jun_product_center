package me.wuwenbin.noteblogv5.constant.uploader;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * layui上传组件返回的json实体类
 * created by Wuwenbin on 2018/7/17 at 14:55
 *
 * @author wuwenbin
 */
@Data
public class LayUploader implements Serializable {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int code;
    private String msg;
    private String imgSrc;
    private Map<String, Object> data = new HashMap<>();

    public LayUploader ok(String message, String imgSrc) {
        LayUploader json = new LayUploader();
        json.setCode(SUCCESS);
        json.setMsg(message);
        data.put("src", imgSrc);
        json.setData(data);
        return json;
    }

    public LayUploader err(String message) {
        LayUploader json = new LayUploader();
        json.setCode(ERROR);
        json.setMsg(message);
        return json;
    }


}
