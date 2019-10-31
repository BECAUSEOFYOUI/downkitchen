package com.self.kitchen.web;

import com.self.kitchen.entity.Collect;
import com.self.kitchen.entity.Food;
import com.self.kitchen.service.CollectService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "收藏菜谱",tags = "收藏菜谱")
public class CollectController {

    @Autowired
    CollectService collectService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation(value = "收藏这个菜谱")
    @PostMapping("/api/collect/collect.do")
    public ResultVo collectFood(@RequestBody Food food) {
        Collect collect = new Collect();
        collect.setfId(food.getId());
        collect.setFoodImg(food.getFoodImg());
        collect.setFoodName(food.getFoodName());
        collectService.save(collect);
        return ResultVo.setOK("OK");
    }

    @ApiOperation(value = "展示收藏的菜谱")
    @GetMapping("/api/collect/list")
    public ResultVo listCollect() {

        return collectService.list();
    }

    @ApiOperation(value = "删除收藏的菜谱")
    @DeleteMapping("/api/collect/delById.do")
    public ResultVo del(int id) {

        return collectService.removeById(id);
    }

}
