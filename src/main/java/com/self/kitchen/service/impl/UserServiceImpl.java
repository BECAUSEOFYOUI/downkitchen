package com.self.kitchen.service.impl;

import com.self.kitchen.dao.UserDao;
import com.self.kitchen.dto.UserDto;

import com.self.kitchen.entity.User;
import com.self.kitchen.service.UserService;
import com.self.kitchen.utils.SmsService;
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
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }

    @Override
    public ResultVo loginOut(String username) {
        /*System.out.println("执行了退出");*/
       String userToken = redisTemplate.opsForValue().get(username);
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
    @Override
    public ResultVo register(String username, String password) {
        User user = userDao.selectUserByUsername(username);
            //数据库没有该手机号
        if(user == null) {
            userDao.save(username, password);//添加到数据库
            String code = SmsService.createRandomVcode();
            //创建对象
            Subject subject = SecurityUtils.getSubject();
            //将验证码先存在session中，用于校验登录;  后面改为存在Redis中
            //subject.getSession().setAttribute("code", code);
            redisTemplate.opsForValue().set("code",code);

            //手机号不为空时，发送短信验证码
            if(username != null) {
                SmsService.send(username, code);
                return ResultVo.setOK("OK");
            }
        }

        return ResultVo.setERROR();
    }

    //再次优化就把用户名、密码、验证码都放在usertoken中
    @Override
    public ResultVo validateCode(String code) {

        System.out.println(code);
        Subject subject = SecurityUtils.getSubject();
        //别老是忘转换数据类型
        //String code1 = (String) subject.getSession().getAttribute("code");
        String code1 = redisTemplate.opsForValue().get("code");
        System.out.println(code1);

        //根据页面修改以下代码
        if (code.equals(code1)) {
            return ResultVo.setOK("OK");
        }

        return ResultVo.setERROR();
    }

    @Override
    public User selectUserByUsername(String username) {

        return userDao.selectUserByUsername(username);

    }

    @Override
    public void save(String username, String password) {
        userDao.save(username, password);
    }

}
