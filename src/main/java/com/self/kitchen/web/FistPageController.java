package com.self.kitchen.web;

import com.self.kitchen.service.FirstPageService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
@CrossOrigin
@Api(value = "首页信息展示",tags = "首页信息展示")
public class FistPageController {

    @Autowired
    FirstPageService firstPageService;

    @ApiOperation(value = "一日三餐推荐",notes = "一日三餐推荐")
    @GetMapping("/api/first/day")
    public ResultVo selectDay(){

        return firstPageService.selectDay();

    }

    @ApiOperation(value = "快手才推荐",notes = "快手菜推荐")
    @GetMapping("/api/first/quickFood")
    public ResultVo quickFoods(){
        return firstPageService.quickFoods();
    }
    @ApiOperation(value = "本周热门菜谱",notes = "本周热门菜谱")
    @GetMapping("/api/first/hotFood")
    public ResultVo hotFood(){
        return firstPageService.hotFood();
    }

    @ApiOperation(value = "更多热门菜谱",notes = "更多热门菜谱")
    @GetMapping("/api/first/hotFoods")
    public ResultVo hotFoods(){
        return firstPageService.hotFoods();
    }
}
