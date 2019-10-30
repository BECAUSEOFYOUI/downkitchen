package com.self.kitchen.service;

import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;

public interface VegetableService {
    ResultVo selectVegetableByCaiPin(User user);
}
