package com.self.kitchen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.dto.HistoryDto;
import com.self.kitchen.entity.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HistoryDao{


    @Select("select * from history where uid=#{id}")
    @ResultType(History.class)
    List<History> list(Integer id);


    @Select("select id from user where username=#{username}")
    @ResultType(Integer.class)
    int selectUserId(String username);

    @Insert("insert into history (food_name,food_img,fid,uid) values(#{food_name},#{food_img},#{fid},#{uid})")
    @ResultType(Integer.class)
    int save(HistoryDto historyDto);

    @Delete("delete from history where id=#{id} and uid=#{uid}")
    int delById(int id, int uid);

}
