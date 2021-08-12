package cc.mrbird.febs.common.core.entity;

import java.util.HashMap;

/**
 * @author MrBird
 */
public class FebsResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public FebsResponse message(String message) {
        this.put("message", message);
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

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
