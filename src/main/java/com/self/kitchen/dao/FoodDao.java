package com.self.kitchen.dao;

import com.self.kitchen.entity.Food;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FoodDao {

    /*@Select("select f.food_name from food f inner join user u on u.id = f.uid where f.uid=#{uid}")
    @ResultType(Foods.class)
    List<Foods> selectFoodByUid(int uid);*/

    @Select("select * from food where fid=#{fid}")
    @ResultType(Food.class)
    Food selectFoodByFid(Integer fids);
}
