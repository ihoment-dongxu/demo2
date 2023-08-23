package com.example.demo.controller;

import com.example.demo.aop.FrequencyControl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

/**
 * @author dongxu
 * @create 2023-08-20 上午10:33
 */
@RestController
@RequestMapping(value = "/dx/v1/test")
public class TestController {

    @GetMapping("/requestContext")
    public void requestContext(@RequestParam(value = "name") String name) {
        // 使用ServletRequestAttributes实现上下文切换
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        requestAttributes.getRequest().setAttribute("name", name);

        String attributeName = (String) requestAttributes.getRequest().getAttribute("name");
        System.out.println(attributeName);
    }

    @GetMapping("/secondKill")
    @FrequencyControl(target = FrequencyControl.Target.UID, time = 10, unit = TimeUnit.MINUTES, count = 1)
    @FrequencyControl(target = FrequencyControl.Target.IP, time = 30, unit = TimeUnit.MINUTES, count = 2)
    public void secondKill() {
        System.out.println("秒杀活动开始了...");
    }

}
