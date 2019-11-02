package com.self.kitchen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.kitchen.dao.FoodDao;
import com.self.kitchen.dao.HistoryDao;
import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.dto.HistoryDto;
import com.self.kitchen.entity.History;
import com.self.kitchen.service.HistoryService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;
    @Autowired
    FoodDao foodDao;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo save(Integer id, String USERTOKEN) {
        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            System.out.println("历史记录令牌"+USERTOKEN);
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);
            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            System.out.println("username"+username);
            int uid = historyDao.selectUserId(username);
            FoodDto foodDto = foodDao.selectFoods(id);
            HistoryDto historyDto = new HistoryDto();
            historyDto.setFid(id);
            historyDto.setFood_name(foodDto.getFoodName());
            historyDto.setFood_img(foodDto.getFoodImg());
            historyDto.setUid(uid);
            int result = historyDao.save(historyDto);
            if(result==1){
                return ResultVo.setOK("添加历史记录成功");
            }else{
                return ResultVo.setERROR("添加历史记录失败");
            }

        }else{

            return ResultVo.setERROR("请先登录");
        }
    }

    @Override
    public ResultVo list(String USERTOKEN) {
        if(USERTOKEN!=null&&!USERTOKEN.equals("")) {
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);
            System.out.println("Collect" + userToken);
            String username = redisTemplate.opsForValue().get("ACCOUNT" + userToken);
            int id = historyDao.selectUserId(username);
            List<History> list = historyDao.list(id);
            return ResultVo.setOK(list);
        } else {
            return ResultVo.setERROR("请先登录");
        }

    }

    @Override
    public ResultVo removeById(int id,String USERTOKEN) {
        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);

            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            int uid = historyDao.selectUserId(username);
            int result =  historyDao.delById(id,uid);
            if(result==1){
                return ResultVo.setOK("删除成功");
            }else{
                return ResultVo.setERROR("删除失败");
            }
        }else{
            return ResultVo.setERROR("请先登录");
        }
    }
}
