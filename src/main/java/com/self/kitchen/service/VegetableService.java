package com.self.kitchen.service;

import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;

public interface VegetableService {
    ResultVo selectVegetableByCaiPin(User user);

    ResultVo deleteByFid(int fid);

    ResultVo deleteByUid(int uid);
}
