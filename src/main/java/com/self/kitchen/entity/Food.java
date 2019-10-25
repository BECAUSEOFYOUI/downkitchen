package com.self.kitchen.entity;

import lombok.Data;

@Data
public class Food {
    private int id;
    private String foodName;
    private String foodImg;
    private String grade;
    private int clickNum;
    private int collectNum;
    private int times;
    private int foodTypeId;

}
