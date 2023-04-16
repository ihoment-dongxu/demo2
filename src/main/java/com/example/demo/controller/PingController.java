package com.example.demo.controller;

import com.example.demo.pojo.result.ResultBean;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongxu
 * @create 2023-04-05 下午2:23
 */
@RestController
@RequestMapping(value = "/portal")
public class PingController {

    @GetMapping(value = "/ping")
    public ResultBean<String> ping() {
        String ping = "ping";
        return ResultBean.ok(ping);
    }

    @PostMapping(value = "/pong")
    public ResultBean<String> pong() {
        String pong = "pong";
        return ResultBean.ok(pong);
    }

}
