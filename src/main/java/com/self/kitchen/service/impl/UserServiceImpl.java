package com.self.kitchen.service.impl;

import com.self.kitchen.dao.UserDao;
import com.self.kitchen.dto.UserDto;

import com.self.kitchen.entity.User;
import com.self.kitchen.service.UserService;
import com.self.kitchen.utils.UUIDUtils;
import com.self.kitchen.vo.ResultVo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public ResultVo login(UserDto userDto) {
        int result = userDao.login(userDto);
        String username = userDto.getUsername();
        if(result==1){
            //创建主题对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌，用户名密码和令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username,userDto.getPassword());

            //登陆告诉shiro登陆成功
            subject.login(token);
            String userToken = UUIDUtils.getUUID()+username;
            System.out.println("token:"+userToken);

            redisTemplate.opsForValue().set(username,userToken);
            redisTemplate.opsForValue().set("ACCOUNT"+userToken,username);
            System.out.println("username键"+"ACCOUNT"+userToken);
            redisTemplate.opsForValue().set("ACCOUNT"+userToken,username,180, TimeUnit.MINUTES);
            return ResultVo.setOK("登陆成功");
        }
        return ResultVo.setERROR();
    }

    @Override
    public ResultVo loginOut(String username) {
        /*System.out.println("执行了退出");*/
       String userToken =  redisTemplate.opsForValue().get(username);
       /* System.out.println("退出时接受的userToken"+userToken);*/
        System.out.println(redisTemplate.opsForValue().get("ACCOUNT"+userToken));
        Boolean flag = redisTemplate.delete("ACCOUNT"+userToken);
        /*System.out.println("缓存是否清楚成功"+flag);*/
        /*System.out.println("清楚缓存后");*/
        System.out.println(redisTemplate.opsForValue().get("ACCOUNT"+userToken));
        if(flag){

            return ResultVo.setOK("退出成功");
        }else{
            return ResultVo.setERROR();
        }
    }

    @Override
    public ResultVo userMessage(String username) {
        String userToken = redisTemplate.opsForValue().get(username);
        if(redisTemplate.hasKey("ACCOUNT" + userToken)){
            String account = redisTemplate.opsForValue().get("ACCOUNT"+userToken);

            User user = userDao.userMessage(account);
            return ResultVo.setOK(user);
        }else{
            return ResultVo.setERROR();
        }
    }


}
