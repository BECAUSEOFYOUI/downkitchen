package com.self.kitchen.web;

import com.self.kitchen.dto.UserDto;
import com.self.kitchen.entity.User;
import com.self.kitchen.service.UserService;
import com.self.kitchen.utils.SmsService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@Api(value ="实现用户的各种操作",tags = "实现用户的各种操作")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/api/user/login.do")
    @ApiOperation(value="实现用户的登陆",notes = "实现用户的登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账户",required = true,dataType = "string"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "string")
    })

    @ResponseBody
    public ResultVo login(UserDto userDto) {
       return userService.login(userDto);
    }

    @GetMapping("/api/user/register.do")
    @ApiOperation(value="实现用户的注册",notes = "实现用户的注册")
    @ResponseBody
    public ResultVo register(String username, String password) {
            /*User user = userService.selectUserByUsername(username);
            //数据库没有该手机号
            if(user == null) {
                userService.save(username, password);//添加到数据库
                return userService.register(username);
            }
        return ResultVo.setERROR();*/

        userService.save(username, password);//添加到数据库
        return userService.register(username);

    }

    @GetMapping("/api/user/validateCode.do")
    @ApiOperation(value="实现验证码的验证",notes = "实现验证码的验证")
    @ResponseBody
    public ResultVo validateCode(String code) {
        return userService.validateCode(code);
    }


    @GetMapping("/api/user/findUser.do")
    @ApiOperation(value="实现用户名的校验",notes = "实现用户名的校验")
    @ResponseBody
    public ResultVo findUser(String username) {
        User user = userService.selectUserByUsername(username);
        if(user != null) {
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }

    @GetMapping("/api/user/fogetPassword.do")
    @ApiOperation(value="实现密码的修改,发送手机验证码",notes = "实现密码的修改")
    @ResponseBody
    public ResultVo fogetPassword (String username) {
        return userService.register(username);
    }




}
