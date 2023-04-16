package com.example.demo.controller;

import com.example.demo.pojo.vo.school.User;
import com.example.demo.service.impl.user.UserWrapBatchService;
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
    public Callable<User> merge(Long userId){
        return () -> userBatchService.queryUser(userId);
    }

}
