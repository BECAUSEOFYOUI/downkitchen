package com.self.kitchen.service;


import com.self.kitchen.entity.History;
import com.self.kitchen.vo.ResultVo;

public interface HistoryService{
    ResultVo save(Integer id, String userToken);

    ResultVo list(String USERTOKEN);

    ResultVo removeById(int id,String USERTOKEN);
}
