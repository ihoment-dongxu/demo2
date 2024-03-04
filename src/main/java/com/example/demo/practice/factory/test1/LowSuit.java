package com.example.demo.practice.factory.test1;

import org.springframework.stereotype.Service;

/**
 * @author DX
 */
@Service(value = "LOW")
public class LowSuit implements Suit {
    @Override
    public void desc() {
        System.out.println("低配版");
    }
}
