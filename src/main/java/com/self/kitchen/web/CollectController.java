package com.self.kitchen.web;

import com.self.kitchen.entity.Collect;
import com.self.kitchen.entity.Food;
import com.self.kitchen.service.CollectService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "收藏菜谱",tags = "收藏菜谱")
public class CollectController {

    @Autowired
    CollectService collectService;

    @ApiOperation(value = "收藏这个菜谱")
    @PostMapping("/api/collect/collect.do")
    public ResultVo collectFood(@RequestBody Food food) {

        Collect collect = new Collect();
        collect.setfId(food.getId());
        collect.setFoodImg(food.getFoodImg());
        collect.setFoodName(food.getFoodName());
        return ResultVo.setResult(collectService.save(collect),"收藏菜谱");
    }

    @ApiOperation(value = "展示收藏的菜谱")
    @GetMapping("/api/collect/list")
    public ResultVo listCollect() {
      List<Collect> list =collectService.list() ;
        for(int i = 0;i < list.size();i++){
            System.out.println("打印list集合"+list.get(i));
        }
        return ResultVo.setResult(true,collectService.list());
    }

    @ApiOperation(value = "删除收藏的菜谱")
    @DeleteMapping("/api/collect/delById.do")
    public ResultVo del(int id) {
        return ResultVo.setResult(collectService.removeById(id),"删除收藏菜谱");
    }

}
