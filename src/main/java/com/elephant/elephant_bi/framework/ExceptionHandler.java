package com.elephant.elephant_bi.framework;

import com.elephant.elephant_bi.utils.Resp;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Resp exception(Exception e){
        return Resp.error(e.getMessage());
    }
}
