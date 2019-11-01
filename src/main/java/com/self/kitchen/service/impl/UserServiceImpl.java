package com.self.kitchen.service.impl;

import com.self.kitchen.dao.UserDao;
import com.self.kitchen.dto.UserDto;

import com.self.kitchen.dto.UserMesDto;

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
            String USERTOKEN =UUIDUtils.getUUID();
            USERTOKEN = USERTOKEN.substring(0,11);
            System.out.println("USERTOKEN"+USERTOKEN);
            String userToken = UUIDUtils.getUUID()+username;
            /*System.out.println("token:"+userToken);*/
            int r = userDao.selectUserToken(USERTOKEN);
            System.out.println("r="+r);
            while(r==1){
                USERTOKEN=UUIDUtils.getUUID();
                USERTOKEN = USERTOKEN.substring(0,11);
                r = userDao.selectUserToken(USERTOKEN);
                System.out.println("r2="+r);
            }
            User user = new User();
            user.setUsertoken(USERTOKEN);
            user.setUsername(username);
            userDao.updateUserToken(user);
            redisTemplate.opsForValue().set(USERTOKEN,userToken,48,TimeUnit.HOURS);
            redisTemplate.opsForValue().set("ACCOUNT"+userToken,username);
            System.out.println("username键"+"ACCOUNT"+userToken);
            redisTemplate.opsForValue().set("ACCOUNT"+userToken,username,180, TimeUnit.MINUTES);
            return ResultVo.setOK(USERTOKEN);
        }
        return ResultVo.setERROR();
    }

    @Override
    public ResultVo loginOut(String USERTOKEN) {
        /*System.out.println("执行了退出");*/
       String userToken =  redisTemplate.opsForValue().get(USERTOKEN);
       /* System.out.println("退出时接受的userToken"+userToken);*/
        /*System.out.println(redisTemplate.opsForValue().get("ACCOUNT"+userToken));*/
        Boolean flag = redisTemplate.delete("ACCOUNT"+userToken);
        /*System.out.println("缓存是否清楚成功"+flag);*/
        /*System.out.println("清楚缓存后");*/
        /*System.out.println(redisTemplate.opsForValue().get("ACCOUNT"+userToken));*/
        if(flag){

            return ResultVo.setOK("退出成功");
        }else{
            return ResultVo.setERROR("你还没有登陆！");
        }
    }

    @Override
    public ResultVo userMessage(String USERTOKEN) {
        String userToken = redisTemplate.opsForValue().get(USERTOKEN);
        if(redisTemplate.hasKey("ACCOUNT" + userToken)){
            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);

            UserMesDto user = userDao.userMessage(username);
            return ResultVo.setOK(user);
        }else{
            return ResultVo.setERROR("请先登陆");
        }
    }

    @Override
    public ResultVo updateUserMessage(UserMesDto userMesDto, String USERTOKEN) {
        String userToken = redisTemplate.opsForValue().get(USERTOKEN);
        if(redisTemplate.hasKey("ACCOUNT" + userToken)){
           String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);

            userMesDto.setUsername(username);
            UserMesDto user1 = userDao.userMessage(username);
            if(userMesDto.getNickname()==null){
                userMesDto.setNickname(user1.getNickname());
            }
            if(userMesDto.getSex()==null){
                userMesDto.setSex(user1.getSex());
            }
            int i = userDao.updateUserMessage(userMesDto);
            if(i==1){
                return ResultVo.setOK("修改成功");
            }else{
                return ResultVo.setERROR();
            }

        }else{
            return ResultVo.setERROR("请先登陆");
        }
    }


    //邓


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

        return ResultVo.setERROR("手机号已经存在");
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


}
