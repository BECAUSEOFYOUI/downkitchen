package com.self.kitchen.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String headImg;
    private String nickname;
    private String Sex;
    private String usertoken;
}
