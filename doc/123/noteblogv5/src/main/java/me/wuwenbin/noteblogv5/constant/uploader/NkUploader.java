package me.wuwenbin.noteblogv5.constant.uploader;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * created by Wuwenbin on 2018/8/4 at 10:28
 *
 * @author wuwenbin
 */
@Data
public class NkUploader implements Serializable {

    public static final String SUCCESS = "000";
    public static final String ERROR = "001";

    private String code;
    private String message;
    private Map<String, Object> item = new HashMap<>();

    public NkUploader ok(String message, String url) {
        NkUploader json = new NkUploader();
        json.setCode(SUCCESS);
        json.setMessage(message);
        item.put("url", url);
        json.setItem(item);
        return json;
    }

    public NkUploader err(String message) {
        NkUploader json = new NkUploader();
        json.setCode(ERROR);
        json.setMessage(message);
        return json;
    }
}
