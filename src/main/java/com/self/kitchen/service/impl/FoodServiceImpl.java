package com.self.kitchen.service.impl;


import com.self.kitchen.dao.FoodDao;

import com.self.kitchen.dao.FoodTypeDao;


import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.dto.TitleType;
import com.self.kitchen.dto.ChildType;
import com.self.kitchen.entity.FoodsType;

import com.self.kitchen.entity.Mariable;
import com.self.kitchen.entity.Step;
import com.self.kitchen.service.FoodService;

import com.self.kitchen.utils.JsonUtils;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodTypeDao foodTypeDao;
    @Autowired
    FoodDao foodDao;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo selectFoodsType() {
        List<TitleType> foodsTitle=null;
        String titleStr = redisTemplate.opsForValue().get("TITLETYPE");
        if(titleStr!=null&&!titleStr.equals("")){
            System.out.println("从缓存中获取foodsTitle");
            foodsTitle=JsonUtils.jsonToList(titleStr,TitleType.class);
        }else{
            System.out.println("从数据库中获取foodsTitle");
            foodsTitle=foodTypeDao.selectFoodsTitle();
            redisTemplate.opsForValue().set("TITLETYPE",JsonUtils.objectToJson(foodsTitle),240,TimeUnit.HOURS);
        }

        List<ChildType> childTypeList = new ArrayList<>();
        for (int i = 0; i < foodsTitle.size(); i++) {
            TitleType t = foodsTitle.get(i);
            ChildType childType = new ChildType();
            childType.setId(t.getId());
            childType.setName(t.getName());
            List<FoodsType> foodsType=null;
            String foodsTypeStr=redisTemplate.opsForValue().get("FOODSTYPE");
            if(foodsTypeStr!=null&&!foodsTypeStr.equals("")){
                System.out.println("从缓存中获取FoodsType");
                foodsType=JsonUtils.jsonToList(foodsTypeStr,FoodsType.class);
            }else{
                System.out.println("从数据库中获取FoodsType");
                foodsType = foodTypeDao.selectFoodsType(t.getId());
                redisTemplate.opsForValue().set("FOODSTYPE",JsonUtils.objectToJson(foodsType),240,TimeUnit.HOURS);
            }

            childType.setChildTypes(foodsType);
            childTypeList.add(childType);
        }
        return ResultVo.setOK(childTypeList);
    }



    @Override
    public ResultVo selectFoods(Integer foodId) {
        FoodDto foodDto=null;
        String jsonStr = redisTemplate.opsForValue().get("FOOD2"+foodId);
        if(jsonStr!=null&&!jsonStr.equals("")){
            System.out.println("从缓存中获取foods");
            foodDto = JsonUtils.jsonToPojo(jsonStr,FoodDto.class);

        }else{

            System.out.println("从数据库中获取Foods");
            foodDto=foodDao.selectFoods(foodId);
            redisTemplate.opsForValue().set("FOOD2"+foodId, JsonUtils.objectToJson(foodDto),240, TimeUnit.HOURS);

            redisTemplate.opsForValue().set("CLICKNUM"+foodId,foodDto.getClickNum()+"",240,TimeUnit.HOURS);
            redisTemplate.opsForValue().set("COLLECTION"+foodId,foodDto.getCollectNum()+"",240,TimeUnit.HOURS);
        }
        foodDto.setClickNum(Integer.valueOf(redisTemplate.opsForValue().get("CLICKNUM"+foodId)));
        foodDto.setCollectNum(Integer.valueOf(redisTemplate.opsForValue().get("COLLECTION"+foodId)));
        return ResultVo.setOK(foodDto);
    }

    @Override
    public ResultVo selectStepById(Integer id) {
        List<Step> steps = null;
        String jsonStr = redisTemplate.opsForValue().get("STEPS"+id);
        if(jsonStr!=null&& !jsonStr.equals("")){
            System.out.println("从缓存中获取Step");
            steps = JsonUtils.jsonToList(jsonStr,Step.class);
        }else{
            System.out.println("从数据库中获取Step");
            steps = foodDao.selectStepByFId(id);
            redisTemplate.opsForValue().set("STEPS"+id,JsonUtils.objectToJson(steps));

        }

        return ResultVo.setOK(steps);
    }

    @Override
    public ResultVo selectMaterial(Integer fid) {
        List<Mariable> mariables = null;
        String str = redisTemplate.opsForValue().get("MARIABLE"+fid);
        if(str!=null&&!str.equals("")){
            System.out.println("从缓存中获取材料");
            mariables=JsonUtils.jsonToList(str,Mariable.class);
        }else{
            System.out.println("从数据库中获取材料");
            mariables=foodDao.selectMaterial(fid);
            redisTemplate.opsForValue().set("MARIABLE"+fid,JsonUtils.objectToJson(mariables));
        }
        return ResultVo.setOK(mariables);
    }

    @Override
    public ResultVo selectAllFoodsByTypeFoodId(Integer id) {
        List<FoodDto> foodDtos = foodDao.selectAllFoods(id);
        return ResultVo.setOK(foodDtos);
    }

}
