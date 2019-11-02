package com.self.kitchen.service;

import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;

public interface VegetableService {
    ResultVo selectVegetable(String USERTOKEN);

    ResultVo deleteByFid(int fid,String USERTOKEN);

    ResultVo deleteByUid(String USERTOKEN);

    ResultVo addByFid(int fid,String USERTOKEN);
}
