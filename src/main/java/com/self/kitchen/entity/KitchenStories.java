package com.self.kitchen.entity;

import lombok.Data;

import java.util.List;

@Data
public class KitchenStories {
    private Integer id;
    private  String  headline;
    private  String text;
    private  Integer sid;//访问次数

    //下面字段是做法步骤
    private List<Img> imgs;

}
