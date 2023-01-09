package com.frankzhou.datastandard.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Component
public class GlobalExceptionHandler {

    // 处理自定义的异常
    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResultDTO<String> customExceptionHandler(HttpServletRequest request,CustomException ex) {
        String methodName = request.getMethod();
        Map<String,String[]> params = request.getParameterMap();
        
        ex.printStackTrace();;
        log.info("Exception Method:{}, args:{}, msg:{}",methodName,params,ex.getMessage());
        
        return ResultDTO.getErrorResult(ResultCodeConstant.SYSTEM_ERROR);
    }
}