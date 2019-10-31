package com.self.kitchen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.kitchen.entity.Collect;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CollectDao {

    @Insert("insert into collect (food_name,food_img,fid) values(#{foodName},#{foodImg},#{fId})")
    void save(Collect collect);


    @Select("select * from collect")
    @ResultType(Collect.class)
    List<Collect> list();


    @Delete("delete from collect where id=#{id}")
    void delById(int id);

}
