package com.example.demo.practice.factory.test1;

import org.springframework.stereotype.Service;

/**
 * @author DX
 */
@Service(value = "HIGH")
public class HighSuit implements Suit {
    @Override
    public void desc() {
        System.out.println("高配版");
    }
}
