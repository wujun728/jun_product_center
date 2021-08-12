package com.sanri.tools.modules.redis.config;

import com.sanri.tools.modules.core.exception.ToolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.StreamCorruptedException;

@RestControllerAdvice
@Slf4j
public class RedisExceptionHandler {

}
