package com.self.kitchen.service;

import com.self.kitchen.vo.ResultVo;

public interface FoodService {
    ResultVo selectFoodsType();

    ResultVo clear();
}
