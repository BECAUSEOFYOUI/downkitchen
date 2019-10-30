package com.self.kitchen.entity;

import lombok.Data;

@Data
public class Food {

    private Integer id;
    private String foodsName;
    private String foodImg;
    private Integer clicknum;
    private Integer foodTypeId;
    private Integer collectnum;
    private Integer uid;

}
