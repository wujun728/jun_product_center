package me.wuwenbin.noteblogv5.constant.uploader;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuwen
 */
@Data
public class MdUploader implements Serializable {

    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    private int success;
    private String message;
    private String url;

    public MdUploader ok(String msg, String url) {
        MdUploader json = new MdUploader();
        json.setSuccess(SUCCESS);
        json.setMessage(msg);
        json.setUrl(url);
        return json;
    }

    public MdUploader err(String msg) {
        MdUploader json = new MdUploader();
        json.setSuccess(ERROR);
        json.setMessage(msg);
        return json;
    }
}
