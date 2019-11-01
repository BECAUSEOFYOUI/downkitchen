package com.self.kitchen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.kitchen.dao.HistoryDao;
import com.self.kitchen.entity.History;
import com.self.kitchen.service.HistoryService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;

    @Override
    public ResultVo save(History history) {
        historyDao.save(history);
        return ResultVo.setOK("OK");
    }

    @Override
    public ResultVo list() {
        List<History> list = historyDao.list();
        return ResultVo.setOK(list);
    }

    @Override
    public ResultVo removeById(int id) {
        historyDao.delById(id);
        return ResultVo.setOK("OK");
    }
}
