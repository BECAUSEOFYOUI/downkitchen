package com.self.kitchen.dao;

import com.self.kitchen.entity.Mariable;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MariableDao {

    @Select("select * from mariable where fid=#{fid}")
    @ResultType(Mariable.class)
    public List<Mariable> selectMariableByFoodId(int foodid);


}
