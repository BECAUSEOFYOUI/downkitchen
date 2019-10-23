package com.self.kitchen.dao;


import com.self.kitchen.entity.User;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
public interface UserDao {
    @Select("select count(1) from admin where username=#{username} and password=#{password}")
    @ResultType(Integer.class)
    int login(User user);
}
