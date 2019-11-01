package com.self.kitchen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.self.kitchen.entity.Collect;
import com.self.kitchen.vo.ResultVo;

import java.util.List;

public interface CollectService{


    ResultVo save(Collect collect);


    ResultVo list();

    ResultVo removeById(int id);
}
