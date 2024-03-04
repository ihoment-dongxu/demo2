package com.example.demo.practice.builder;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDirector {

    public User createBuilder(String username, String email, String address, int age) {
        return new UserBuilder().buildName(username)
                .buildAddress(address)
                .buildAge(age)
                .buildEmail(email)
                .build();
    }
}
