package com.self.kitchen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.self.kitchen.entity.History;
import com.self.kitchen.vo.ResultVo;

public interface HistoryService{
    ResultVo save(History history);

    ResultVo list();

    ResultVo removeById(int id);
}
