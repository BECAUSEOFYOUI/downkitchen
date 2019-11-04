package com.self.kitchen.web;

import com.self.kitchen.entity.KitchenStories;
import com.self.kitchen.service.NewsService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(value = "新闻快讯",tags = "厨房新闻")
public class NewsController {
    @Autowired
    NewsService newsService;
    @ApiOperation(value = "页面加载时执行获取所有的新闻类型")
    @GetMapping("/list")
    public ResultVo list(){
        return ResultVo.setOK(newsService.list());
    }
    @ApiOperation(value = "根据新闻类型进行访问，需要新闻类型ID1-10")
    @PostMapping("/category/{*}")
    public ResultVo category(@PathVariable("*") String num){
        return ResultVo.setOK(newsService.selectCategory(Integer.valueOf(num)));
    }
    @ApiOperation(value = "根据新闻列表访问，需要新闻对应的ID")
    @PostMapping("/hottopic/{*}")
    public ResultVo HotTopic(@PathVariable("*") String num){
        return ResultVo.setOK(newsService.selectHotTopic(Integer.valueOf(num)));
    }
    @ApiOperation(value = "根据列表ID对该新闻进行详情查询")
    @PostMapping("/details/{*}")
    public ResultVo Details(@PathVariable("*") Integer id){
        return ResultVo.setOK(newsService.selectDetails(Integer.valueOf(id)));
    }
}
