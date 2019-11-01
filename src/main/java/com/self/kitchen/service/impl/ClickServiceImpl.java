package com.self.kitchen.service.impl;

import com.self.kitchen.service.ClickService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClickServiceImpl implements ClickService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public ResultVo confirmClick(String USERTOKEN) {
        String userToken = redisTemplate.opsForValue().get(USERTOKEN);
        String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);


        return null;
    }
}
