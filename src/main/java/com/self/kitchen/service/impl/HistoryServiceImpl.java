package com.self.kitchen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.kitchen.dao.HistoryDao;
import com.self.kitchen.entity.History;
import com.self.kitchen.service.HistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryDao, History> implements HistoryService {
}
