package com.example.demo.controller;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.BusinessExceptionEnum;
import com.example.demo.pojo.result.ResultBean;
import com.example.demo.pojo.vo.school.User;
import com.example.demo.service.impl.user.UserWrapBatchService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:43
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserWrapBatchService userBatchService;

    @GetMapping(value = "/merge")
    @ApiModelProperty(value = "请求合并")
    public Callable<User> merge(Long userId) {
        return () -> userBatchService.queryUser(userId);
    }

    @GetMapping(value = "/businessException")
    @ApiModelProperty(value = "请求合并")
    public ResultBean<Boolean> businessException(Long userId) {
        if (userId == 1) {
            throw new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
        }
        return ResultBean.ok(Boolean.TRUE);
    }


}
