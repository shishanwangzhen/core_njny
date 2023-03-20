package com.corewell.study.handler;

import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/02/27/16:01
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultMsg errorHandler(Exception ex) {
        log.error("统一异常拦截", ex);
        return ResultMsg.error(10000, ex.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResultMsg runtimeExceptionHandler(RuntimeException ex) {
        log.error("RuntimeException异常拦截", ex);
        if (ResultStatusCode.INVALID_TOKEN.getErrmsg().equals(ex.getMessage())) {
            return new ResultMsg(ResultStatusCode.INVALID_TOKEN);
        } else if (ResultStatusCode.TOKEN_IS_NULL.getErrmsg().equals(ex.getMessage())) {
            return new ResultMsg(ResultStatusCode.TOKEN_IS_NULL);
        }
        return ResultMsg.error(10000, ex.getMessage());
    }
}
