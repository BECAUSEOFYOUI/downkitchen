package com.self.kitchen.dao;

import com.self.kitchen.dto.FoodDto;


import com.self.kitchen.entity.Food;
import com.self.kitchen.entity.Mariable;
import com.self.kitchen.entity.Step;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FoodDao {
    @Select("select f.*,u.username from food f,user u where f.id =#{foodId} and f.uid=u.id")
    @Results(id = "foodDtoMap",
                value = {
                @Result(property = "id",column = "id"),
                @Result(column = "food_name",property = "foodName"),
                @Result(column = "food_img",property = "foodImg"),
                @Result(column = "clicknum",property = "clickNum"),
                @Result(column = "collectnum",property = "collectNum"),
                @Result(column = "username",property = "username"),
                @Result(column = "fooddetail",property = "foodDetail"),

    })
    @ResultType(FoodDto.class)
    FoodDto selectFoods(@Param("foodId") Integer foodId);

    @Select("select * from step where fid=#{id} order by steps asc")
    @Results(id = "FoodStep", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "stepDesc", column = "step_desc")
    })
    List<Step> selectStepByFId(@Param("id") Integer id);

    @Select("select * from mariable where fid=#{fid}")
    List<Mariable> selectMaterial(Integer fid);

    @Select("select * from food where fid=#{fid}")
    @ResultType(Food.class)
    Food selectFoodByFid(Integer fids);

    @Select("select f.*,u.username from food f,user u where foodTypeId=#{id} and f.uid=u.id")
    @ResultMap("foodDtoMap")
    @ResultType(FoodDto.class)
    List<FoodDto> selectAllFoods(Integer id);
}
