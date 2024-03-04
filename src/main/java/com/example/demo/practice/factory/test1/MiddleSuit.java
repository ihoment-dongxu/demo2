package com.example.demo.practice.factory.test1;

import org.springframework.stereotype.Service;

/**
 * @author DX
 */
@Service(value = "MIDDLE")
public class MiddleSuit implements Suit {
    @Override
    public void desc() {
        System.out.println("中配版");
    }
}
