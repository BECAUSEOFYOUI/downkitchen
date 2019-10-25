package com.self.kitchen.dto;

import lombok.Data;

@Data
public class FoodDto {
    private int id;
    private String foodName;
    private String foodImg;
    private int clickNum;
    private int times;
    private int collectNum;
    private int uid;
    private String username;

}
