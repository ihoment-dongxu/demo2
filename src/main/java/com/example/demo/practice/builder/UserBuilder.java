package com.example.demo.practice.builder;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder buildName(String name){
        user.setName(name);
        return this;
    }
    public UserBuilder buildAge(int age){
        user.setAge(age);
        return this;
    }
    public UserBuilder buildAddress(String address){
        user.setAddress(address);
        return this;
    }
    public UserBuilder buildEmail(String email){
        user.setEmail(email);
        return this;
    }

    public User build(){
        return user;
    }

}
