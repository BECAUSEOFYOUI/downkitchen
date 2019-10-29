package com.self.kitchen.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("food")
public class Food {
    private int id;
    private String foodName;
    private String foodImg;
    private double grade;
    private int time;
    private int tId;

    public Food() {
    }

    public Food(int id, String foodName, String foodImg, double grade, int time, int tId) {
        this.id = id;
        this.foodName = foodName;
        this.foodImg = foodImg;
        this.grade = grade;
        this.time = time;
        this.tId = tId;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }
}
