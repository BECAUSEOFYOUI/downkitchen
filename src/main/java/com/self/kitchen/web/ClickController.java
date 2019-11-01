package com.self.kitchen.web;

import com.self.kitchen.service.ClickService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户对菜谱点赞等操作",tags = "用户对菜谱点赞等操作")
public class ClickController {

    @Autowired
    ClickService clickService;
    @PostMapping("/api/click/confirmClick")
    @ApiOperation(value = "用户为菜谱点赞了",notes = "用户为菜谱点赞接口")
    public ResultVo confirmClick(String username){
        if(username == null){
            return ResultVo.setERROR("请先登录");
        }else{
            return clickService.confirmClick(username);
        }

    }

}
