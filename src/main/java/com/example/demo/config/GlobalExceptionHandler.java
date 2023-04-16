package com.example.demo.config;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.BusinessExceptionEnum;
import com.example.demo.pojo.result.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 全局异常处理
 *
 * @author dongxu
 * @create 2023-04-16 下午1:03
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public <T> ResultBean<T> businessExceptionHandler(HttpServletRequest req, BusinessException e) {
        String desc = Optional.ofNullable(e).map(BusinessException::getExceptionEnum).map(BusinessExceptionEnum::getDescription).orElse("");
        Integer code = Optional.ofNullable(e).map(BusinessException::getExceptionEnum).map(BusinessExceptionEnum::getCode).orElse(0);
        String msg = Optional.ofNullable(e).map(BusinessException::getMsg).orElse("");

        String info;
        if (StringUtils.isEmpty(desc)) {
            info = !StringUtils.isEmpty(msg) ? msg : "Unknown error";
        } else {
            info = !StringUtils.isEmpty(msg) ? desc + "," + msg : desc;
        }

        log.error("业务发生异常，请求路径={}，异常原因={}", req.getRequestURI(), info);
        return ResultBean.fail(code, info);
    }


}
