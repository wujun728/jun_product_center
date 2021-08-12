package com.sanri.tools.modules.core.exception;

public class ToolException extends RuntimeException {
    public ToolException(String message) {
        super(message);
    }

    public ToolException(String message, Throwable cause) {
        super(message, cause);
    }
}
