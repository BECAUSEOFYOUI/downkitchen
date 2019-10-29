package com.self.kitchen.service.impl;


import com.self.kitchen.dao.FoodDao;

import com.self.kitchen.dao.FoodTypeDao;


import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.dto.TitleType;
import com.self.kitchen.dto.ChildType;
import com.self.kitchen.entity.FoodsType;

import com.self.kitchen.service.FoodService;

import com.self.kitchen.utils.JsonUtils;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;


import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

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
        List<TitleType> foodsTitle = foodTypeDao.selectFoodsTitle();
        List<ChildType> childTypeList = new ArrayList<>();
        for (int i = 0; i < foodsTitle.size(); i++) {
            TitleType t = foodsTitle.get(i);
            ChildType childType = new ChildType();
            childType.setId(t.getId());
            childType.setName(t.getName());
            List<FoodsType> foodsType = foodTypeDao.selectFoodsType(t.getId());
            childType.setChildTypes(foodsType);
            childTypeList.add(childType);
        }
        return ResultVo.setOK(childTypeList);
    }



    @Override
    public ResultVo selectFoods(Integer foodId) {
        FoodDto foodDto=null;
        String jsonStr = redisTemplate.opsForValue().get("FOOD2");
        if(jsonStr==null){
            System.out.println("从数据库中获取");
            foodDto=foodDao.selectFoods(foodId);
            redisTemplate.opsForValue().set("FOOD2", JsonUtils.objectToJson(foodDto));
        }else{
            System.out.println("从缓存中获取");
            foodDto = JsonUtils.jsonToPojo(redisTemplate.opsForValue().get("FOOD2"),FoodDto.class);
        }

        return ResultVo.setOK(foodDto);
    }

    @Override
    public ResultVo selectStepById(Integer id) {

        return ResultVo.setOK(foodDao.selectStepByFId(id));
    }

    @Override
    public ResultVo selectMaterial(Integer fid) {
        return ResultVo.setOK(foodDao.selectMaterial(fid));
    }

}
