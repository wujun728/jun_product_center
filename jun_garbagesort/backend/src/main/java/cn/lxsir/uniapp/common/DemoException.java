package cn.lxsir.uniapp.common;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DemoException extends Exception {
    public DemoException(String message) {
        super(message);
        log.error(message);
    }
}
