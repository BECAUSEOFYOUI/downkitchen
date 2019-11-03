package com.self.kitchen.service;

import com.self.kitchen.vo.ResultVo;

public interface FoodService {
    ResultVo selectFoodsType();

    ResultVo selectFoods(Integer foodId);


    ResultVo selectStepById(Integer id);

    ResultVo selectMaterial(Integer fid);

    ResultVo selectAllFoodsByTypeFoodId(Integer id);
}
