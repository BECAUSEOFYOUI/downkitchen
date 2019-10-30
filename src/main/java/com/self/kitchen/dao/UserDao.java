package com.self.kitchen.dao;


import com.self.kitchen.dto.UserDto;


import com.self.kitchen.entity.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
public interface UserDao {
    @Select("select count(1) from user where username=#{username} and password=#{password}")
    @ResultType(Integer.class)
    int login(UserDto userDto);

    @Select("select * from user where username=#{account}")
    @ResultType(User.class)
    User userMessage(String account);

    /*@Select("select * from user")
    @ResultType(User.class)
    User loginUser();*/
}
