package com.example.demo.practice.factory.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author DX
 */
@Component
public class SuitFactory {

    @Autowired
    private Map<String, Suit> suitMap;

    public Suit getSuit(String type) {
        return suitMap.get(type);
    }
}
