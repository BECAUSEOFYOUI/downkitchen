package com.self.kitchen.web;

import com.self.kitchen.service.FoodService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(value = "食物类型对应的各种数据",tags = "食物类型对应的各种数据")
public class FoodsController {
    @Autowired
    FoodService foodService;
    @Resource
    private RedisTemplate<String,String> stringRedisTemplate;
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

    @GetMapping("/api/food/selectStepByFId")
    @ApiOperation(value = "查询食物制作步骤",notes = "查询食物制作步骤")
    @ApiImplicitParam(paramType = "query",name = "id",value = "食物的id",required = true,dataType = "int")
    public ResultVo selectStepById(Integer id){
        return foodService.selectStepById(id);
    }

    @GetMapping("/api/food/selectMaterial")
    @ApiOperation(value = "查询食物的用料",notes = "查询食物的用料")
    @ApiImplicitParam(name = "fid",value = "食物的id",required = true,dataType = "int")
    public ResultVo selectMaterial(Integer fid){
        return foodService.selectMaterial(fid);
    }

    @GetMapping("/api/food/redisAdd")
    public void saveRedis(){

        stringRedisTemplate.opsForValue().set("b","test");
    }
    @GetMapping("/api/food/redisGet")
    public String getRedis(){
        return stringRedisTemplate.opsForValue().get("a");
    }
}
