package com.self.kitchen.dao;

import com.self.kitchen.entity.Vegetable;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface VegetableDao {

    @Select("select * from cai_lan_zi where uid=#{uid}")
    @ResultType(Vegetable.class)
    Vegetable selectByUid(Integer uid);
}
