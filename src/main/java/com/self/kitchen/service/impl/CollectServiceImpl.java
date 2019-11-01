package com.self.kitchen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.kitchen.dao.CollectDao;
import com.self.kitchen.entity.Collect;
import com.self.kitchen.service.CollectService;

import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CollectServiceImpl  implements CollectService {
    @Autowired
    CollectDao collectDao;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo save(Collect collect) {
        collectDao.save(collect);

        return ResultVo.setOK("OK");
    }

    @Override
    public ResultVo list() {
        List<Collect> list = collectDao.list();
        return ResultVo.setOK(list);
    }

    @Override
    public ResultVo removeById(int id) {
        collectDao.delById(id);
        return ResultVo.setOK("OK");
    }


}
