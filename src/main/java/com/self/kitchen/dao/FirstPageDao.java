package com.self.kitchen.dao;

import com.self.kitchen.entity.Food;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FirstPageDao {
    @Select("select * from food where foodTypeId=#{id} order by collectnum desc limit 0,2")
    List<Food> selectDay(int id);

    @Select("select * from food where foodTypeId=1 order by collectnum  desc limit 0,2;")
    List<Food> quickFoods();
    @Select("select * from food order by collectnum desc limit 0,2")
    List<Food> hotFood();

    @Select("select * from food  order by collectnum desc limit 0,50")
    List<Food> hotFoods();
}
