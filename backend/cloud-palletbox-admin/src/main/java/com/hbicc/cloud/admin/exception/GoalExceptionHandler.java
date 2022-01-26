package com.hbicc.cloud.admin.exception;
import com.hbicc.cloud.common.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class GoalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseUtil exceptionHandler(Exception e) {
        return ResponseUtil.error(-404, e.getMessage(), null);
    }
}
