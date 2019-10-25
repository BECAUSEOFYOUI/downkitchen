package com.self.kitchen.web;

import com.self.kitchen.service.FoodService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "食物类型对应的各种数据",tags = "食物类型对应的各种数据")
public class FoodsController {
    @Autowired
    FoodService foodService;
    /*@GetMapping("api/food/selectFoods")
    public ResultVo selectFoods(int titleTypeId,int foodTypeId){
        return ResultVo.setResult(true,foodService.selectFoods(titleTypeId,typeNameId));
    }*/

    @GetMapping("api/food/selectFoodsType")
    @ApiOperation(value = "食物的分类展示",notes = "食物的分类展示")
    public ResultVo selectFoodTitle(){
        return foodService.selectFoodsType();
    }

}
