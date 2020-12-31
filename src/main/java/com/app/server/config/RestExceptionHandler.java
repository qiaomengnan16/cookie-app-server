package com.app.server.config;

import com.app.server.util.Result;
import com.app.server.util.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result ExceptionHandler(HttpServletResponse response, ServiceException ex) {
        response.setStatus(200);
        return new Result(ex.getStatus(), ex.getMessage(), null);
    }

}
