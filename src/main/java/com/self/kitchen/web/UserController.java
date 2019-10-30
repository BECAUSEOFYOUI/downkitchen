package com.self.kitchen.web;

import com.self.kitchen.dto.UserDto;
import com.self.kitchen.entity.User;
import com.self.kitchen.service.UserService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@Api(value ="实现用户的各种操作",tags = "实现用户的各种操作")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/api/user/login.do")
    @ApiOperation(value="实现用户的登陆",notes = "实现用户的登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "string"),
            @ApiImplicitParam(name = "username",value = "账户",required = true,dataType = "string"),

    })
    @ResponseBody
    public ResultVo login(UserDto userDto) {
       return userService.login(userDto);
    }

    @ResponseBody
    @DeleteMapping("/api/user/loginOut")
    @ApiOperation(value = "用户退出",notes = "用户退出")
    @ApiImplicitParam(name = "username",value = "账户",required = true,dataType = "string")
    public ResultVo loginOut(String username){
        ResultVo resultVo = userService.loginOut(username);
        System.out.println(resultVo.getData());
        return resultVo;
    }

    @GetMapping("/api/user/userMessage")
    @ResponseBody
    @ApiOperation(value="用户信息展示",notes = "用户信息展示")
    @ApiImplicitParam(name = "username",value = "账户",dataType = "string")
    public ResultVo userMessage(String username){
        return userService.userMessage(username);
    }

    @GetMapping("/api/user/register.do")
    @ApiOperation(value="实现用户的注册",notes = "实现用户的注册")
    @ResponseBody
    public ResultVo register(String username, String password) {

        return userService.register(username,password);

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
    public ResultVo fogetPassword (String username, String password) {
        return userService.register(username, password);
    }

}
