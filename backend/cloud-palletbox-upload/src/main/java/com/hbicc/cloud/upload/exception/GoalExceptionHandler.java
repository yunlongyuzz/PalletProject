package com.hbicc.cloud.upload.exception;
import com.hbicc.cloud.common.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class GoalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseUtil exceptionHandler(Exception e) {
        return ResponseUtil.error(-404, e.getMessage(), null);
    }


}
