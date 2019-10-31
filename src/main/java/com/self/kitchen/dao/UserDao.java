package com.self.kitchen.dao;


import com.self.kitchen.dto.UserDto;


import com.self.kitchen.entity.Food;
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

    @Select("select * from user where username = #{username}")
    @ResultType(Integer.class)
    User selectUserByUsername(String username);

    @Select("insert into user(username, password) values(#{username}, #{password})")
    @ResultType(Integer.class)
    void save(String username, String password);

    /*@Select("select f.food_name,m.* from food f inner join (user u, mariable m) on(u.id=f.uid, f.id=m.fid) " +
            "where u.id=#{uid}")
    @ResultType(Integer.class)
    Map<String, List<Mariable>> selectVegetableByCaiPin(int uid);*/


    @Select("select food_name from user where username = #{username}")
    @ResultType(Integer.class)
    Food selectFoodByUid(int uid);
}
