package com.sanri.tools.modules.core.exception;



import com.sanri.tools.modules.core.dtos.ResponseDto;

import java.text.MessageFormat;

public interface ExceptionCause<T extends Exception> {
    T exception(Object... args);

    ResponseDto result();

    MessageFormat getMessageFormat();
}
