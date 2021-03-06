package com.self.kitchen.web;

import com.self.kitchen.dto.UserDto;
import com.self.kitchen.dto.UserMesDto;

import com.self.kitchen.service.UserService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@Api(value ="实现用户的各种操作",tags = "实现用户的各种操作")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/api/user/login")
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
    @ApiImplicitParam(name = "USERTOKEN",value = "私密令牌",required = true,dataType = "string")
    public ResultVo loginOut(String USERTOKEN){
        ResultVo resultVo = userService.loginOut(USERTOKEN);
        System.out.println(resultVo.getData());
        return resultVo;
    }

    @GetMapping("/api/user/userMessage")
    @ResponseBody
    @ApiOperation(value="用户信息展示",notes = "用户信息展示")
    @ApiImplicitParam(name = "USERTOKEN",value = "私密令牌",dataType = "string",required = true)
    public ResultVo userMessage(String USERTOKEN){
        return userService.userMessage(USERTOKEN);
    }


    /*图片上传给个隐藏input框,吧图片地址赋给input框*/
    @PutMapping("/api/user/updateMessage")
    @ResponseBody
    @ApiOperation(value ="用户信息修改",notes = "用户信息修改")
    @ApiImplicitParam(name = "USERTOKEN",value = "私密令牌",dataType = "string",required = true)
    public ResultVo updateUserMessage(UserMesDto userMesDto, String USERTOKEN){

        return userService.updateUserMessage(userMesDto,USERTOKEN);
    }


    //邓
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

    /*@GetMapping("/api/user/fogetPassword.do")
    @ApiOperation(value="实现密码的修改,发送手机验证码",notes = "实现密码的修改")
    @ResponseBody
    public ResultVo fogetPassword (String username, String password) {
        return userService.register(username, password);
    }
*/
    @PutMapping("/api/user/updatePwd")
    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "USERTOKNE",value = "用户秘钥",type = "string",required = true),
            @ApiImplicitParam(name = "password",value = "用户修改的密码",type = "string",required = true),
    })

    public ResultVo updatePwd(String USERTOKEN,String password){
        return userService.updatePwd(USERTOKEN,password);
    }

}
