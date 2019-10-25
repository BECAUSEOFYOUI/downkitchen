package com.self.kitchen.dao;

import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.entity.Food;

import org.apache.ibatis.annotations.*;

public interface FoodDao {
    @Select("select f.*,u.username from food f,user u where f.id =#{foodId} and f.uid=u.id")
    @Results(id = "foodDtoMap",
                value = {
                @Result(property = "id",column = "id"),
                @Result(column = "food_name",property = "foodName"),
                @Result(column = "food_img",property = "foodImg"),
                @Result(column = "clicknum",property = "clickNum"),
                @Result(column = "times",property = "times"),
                @Result(column = "collectnum",property = "collectNum"),
                @Result(column = "username",property = "username"),

    })
    @ResultType(FoodDto.class)
    FoodDto selectFoods(Integer foodId);

}
