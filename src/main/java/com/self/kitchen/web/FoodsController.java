package com.self.kitchen.web;

import com.self.kitchen.service.FoodService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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


    @GetMapping("/api/food/selectFoods")
    @ApiOperation(value = "查询食物的信息",notes = "查询食物的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "食物ID",required = true,dataType = "int")
    })
    public ResultVo selectFoods(Integer id){
        return foodService.selectFoods(id);
    }


}
