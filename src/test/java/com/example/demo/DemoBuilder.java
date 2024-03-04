package com.example.demo;

import com.example.demo.practice.builder.User;
import com.example.demo.practice.builder.UserDirector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoBuilder {

    @Resource
    private UserDirector userDirector;

    @Test
    void builder() {
        User user = userDirector.createBuilder("dongxu", "dongxu@qq.com", "China", 26);
        System.out.println(user);
    }

}
