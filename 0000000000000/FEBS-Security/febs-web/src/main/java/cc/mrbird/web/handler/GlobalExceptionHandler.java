package cc.mrbird.web.handler;

import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.exception.FileDownloadException;
import cc.mrbird.common.exception.LimitAccessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LimitAccessException.class)
    @ResponseBody
    public ResponseBo handleLimitAccessException(LimitAccessException e) {
        return ResponseBo.overClocking(e.getMessage());
    }

    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseBody
    public ResponseBo handleFileDownloadException(LimitAccessException e) {
        return ResponseBo.error(e.getMessage());
    }

}
