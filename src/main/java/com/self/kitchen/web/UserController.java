package com.self.kitchen.web;

import com.self.kitchen.dto.UserDto;
import com.self.kitchen.service.UserService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
            @ApiImplicitParam(name = "username",value = "账户",required = true,dataType = "string"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "string")
    })
    @ResponseBody
    public ResultVo login(UserDto userDto) {
       return userService.login(userDto);
    }
}
