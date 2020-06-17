package com.brainboom.matterwiki4jboot.controller.advices;


import com.brainboom.matterwiki4jboot.response.CommResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class MwikiCtrlExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(Exception.class)
    public CommResponse CommonExceptionHandler(Exception e) {

        log.error("ip:[{}],url:[{}][{}],params:[{}],exception:{}",
                request.getRemoteAddr(),
                request.getMethod(),
                request.getRequestURL(),

                request.getParameterMap(),
                e.toString());
        log.error("",e);
        return CommResponse.error("", e.getMessage());
    }


}
