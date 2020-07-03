package com.hyl.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
//@ConditionalOnWebApplication
public class GlobalExceptionHandler {

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error(e.getMessage(), e);

//        if (ENV_PROD.equals(profile)) {
//            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
//            return HttpResult.fail(ErrorMsgEnum.INTERNET_ERROR.getValue());
//        }
        return "false";
    }
}
