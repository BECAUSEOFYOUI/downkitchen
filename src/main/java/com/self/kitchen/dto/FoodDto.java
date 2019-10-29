package com.self.kitchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FoodDto implements Serializable {
    private Integer id;
    private String foodName;
    private String foodImg;
    private int clickNum;
    private int times;
    private int collectNum;
    private Integer uid;
    private String username;

}
