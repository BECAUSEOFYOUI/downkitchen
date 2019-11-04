package com.self.kitchen.service;

import com.self.kitchen.vo.ResultVo;

public interface FirstPageService {
    ResultVo selectDay();

    ResultVo quickFoods();

    ResultVo hotFood();

    ResultVo hotFoods();
}
