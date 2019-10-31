package com.self.kitchen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.kitchen.entity.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HistoryDao{

    @Insert("insert into history (food_name,food_img,fid) values(#{foodName},#{foodImg},#{fId})")
    void save(History history);

    @Select("select * from history")
    @ResultType(History.class)
    List<History> list();

    @Delete("delete from history where id=#{id}")
    void delById(int id);
}
