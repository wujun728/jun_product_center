package cc.mrbird.febs.common.domain;

import java.util.HashMap;

public class FebsResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public FebsResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public FebsResponse code(String code) {
        this.put("code", code);
        return this;
    }

    public FebsResponse status(String status) {
        this.put("status", status);
        return this;
    }

    public FebsResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public FebsResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public FebsResponse addCodeMessage(Integer code,String message,String status,Object data) {
        this.put("code", code);
        this.put("message", message);
        this.put("status", status);
        this.put("data", data);
        return this;
    }

    public FebsResponse addCodeMessage(Integer code,String message,String status) {
        this.put("code", code);
        this.put("message", message);
        this.put("status", status);
        return this;
    }
}
