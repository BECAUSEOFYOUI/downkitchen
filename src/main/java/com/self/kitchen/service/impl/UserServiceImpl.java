package com.self.kitchen.service.impl;

import com.self.kitchen.dao.UserDao;
import com.self.kitchen.dto.UserDto;
import com.self.kitchen.service.UserService;
import com.self.kitchen.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public ResultVo login(UserDto userDto) {
        int result = userDao.login(userDto);
        if(result==1){
            //创建主题对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌，用户名密码和令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(),userDto.getPassword());

            //登陆告诉shiro登陆成功
            subject.login(token);
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }
}
