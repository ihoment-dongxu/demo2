package com.example.demo.pojo.vo.school;

import lombok.Data;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:44
 */
@Data
public class User {
    private String userId;

    private String name;

    public User(Long userId) {
        this.userId = userId.toString();
        this.name = "User " + userId;
    }
}
