package com.self.kitchen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("history")
public class History {
    private int id;
    private String foodName;
    private String foodImg;
    @TableField("fId")
    private int fId;

    @Override
    public String toString() {
        return "HistoryService{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodImg='" + foodImg + '\'' +
                ", fId=" + fId +
                '}';
    }

    public History() {
    }

    public History(int id, String foodName, String foodImg, int fId) {
        this.id = id;
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.fId = fId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }
}
