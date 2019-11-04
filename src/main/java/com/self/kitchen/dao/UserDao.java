package com.self.kitchen.dao;


import com.self.kitchen.dto.UserDto;


import com.self.kitchen.dto.UserMesDto;


import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {
    @Select("select count(1) from user where username=#{username} and password=#{password}")
    @ResultType(Integer.class)
    int login(UserDto userDto);

    @Select("select * from user where username=#{username}")
    /*@ResultType(UserMesDto.class)*/
    UserMesDto userMessage(String username);

    @Update("update user set nickname=#{nickname},sex=#{sex},headImg=#{headImg} where username=#{username}")
    @ResultType(Integer.class)
    int updateUserMessage(UserMesDto userMesDto);

    @Select("select count(1) from user where userToken=#{usertoken}")
    int selectUserToken(String usertoken);
    @Update("update user set usertoken=#{usertoken} where username=#{username}")
    @ResultType(Integer.class)
    int updateUserToken(User user);



    //邓
    @Select("select * from user where username = #{username}")
    @ResultType(Integer.class)
    User selectUserByUsername(String username);

    @Select("insert into user(username, password) values(#{username}, #{password})")
    @ResultType(Integer.class)
    void save(String username, String password);

    @Update("update user set password=#{password} where usertoken=#{usertoken}")
    int updatePwd(String usertoken,String password);

    /*@Select("select * from user")
    @ResultType(User.class)
    User loginUser();*/
}
