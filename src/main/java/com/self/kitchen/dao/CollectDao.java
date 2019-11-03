package com.self.kitchen.dao;


import com.self.kitchen.dto.CollectDto;
import com.self.kitchen.entity.Collect;

import org.apache.ibatis.annotations.*;


import java.util.List;

public interface CollectDao {

    @Insert("insert into collect (food_name,food_img,fid,uid) values(#{food_name},#{food_img},#{fid},#{uid})")
    @ResultType(Integer.class)
    int save(CollectDto collectDto);


    @Select("select * from collect where uid=#{id}")
    @ResultType(Collect.class)
    List<Collect> listCollect(Integer id);


    @Delete("delete from collect where id=#{id} and uid=#{uid}")
    int delById(int id,int uid);

    @Select("select  id from user where username=#{username}")
    @ResultType(Integer.class)
    int selectUserId(String username);
}
