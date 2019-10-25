package com.self.kitchen.dao;

import com.self.kitchen.entity.FoodsType;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FoodTypeDao {
    @Select("select * from foodtype where titleTypeId=#{foodTitleTypeId}")
    @ResultType(FoodsType.class)
    List<FoodsType> selectFoodsType(int foodTitleTypeId);
    @Select("select * from titleType")
    @ResultType(TitleType.class)
    List<TitleType> selectFoodsTitle();
}
