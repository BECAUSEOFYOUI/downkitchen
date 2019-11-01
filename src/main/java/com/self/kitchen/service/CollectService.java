package com.self.kitchen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.self.kitchen.entity.Collect;
import com.self.kitchen.vo.ResultVo;

import java.util.List;

public interface CollectService{


    ResultVo listCollect(String USERTOKEN);

    ResultVo removeById(int id,String USERTOKEN);

    ResultVo collectFood(Integer id, String userToken);
}
