package com.example.demo;

import com.example.demo.practice.factory.test1.Suit;
import com.example.demo.practice.factory.test1.SuitFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoFactory {
    @Resource
    private SuitFactory suitFactory;
    @Test
    void factory(){
        String type = "MIDDLE";
        Suit factorySuit = suitFactory.getSuit(type);
        factorySuit.desc();
    }

}
