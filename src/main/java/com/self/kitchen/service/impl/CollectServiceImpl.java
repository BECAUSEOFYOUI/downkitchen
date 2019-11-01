package com.self.kitchen.service.impl;


import com.self.kitchen.dao.CollectDao;
import com.self.kitchen.dao.FoodDao;
import com.self.kitchen.dto.CollectDto;
import com.self.kitchen.dto.FoodDto;
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
    @Autowired
    private FoodDao foodDao;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo listCollect(String USERTOKEN) {
        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);
            System.out.println("Collect"+userToken);
            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            int id = collectDao.selectUserId(username);
            System.out.println(""+id);
            List<Collect> list = collectDao.listCollect(id);
            for (Collect collect : list) {
                System.out.println(collect);
            }
            return ResultVo.setOK(list);
        }else{
            return ResultVo.setERROR("请先登录");
        }
    }
    @Override
    public ResultVo removeById(int id,String USERTOKEN) {
        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);

            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            int uid = collectDao.selectUserId(username);
           int result =  collectDao.delById(id,uid);
           if(result==1){
               return ResultVo.setOK("删除成功");
           }else{
               return ResultVo.setERROR("删除失败");
           }
        }else{
            return ResultVo.setERROR("请先登录");
        }

    }

    @Override
    public ResultVo collectFood(Integer id, String USERTOKEN) {

        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            System.out.println("搜藏令牌"+USERTOKEN);
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);
            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            System.out.println("username"+username);
            int uid = collectDao.selectUserId(username);
            System.out.println("uid"+uid);
            System.out.println("id"+id);
            FoodDto foods = foodDao.selectFoods(id);
            CollectDto collectDto = new CollectDto();
            collectDto.setFid(id);
            collectDto.setUid(uid);
            collectDto.setFood_img(foods.getFoodImg());
            collectDto.setFood_name(foods.getFoodName());
            /*System.out.println(collectDto.getFood_img()+collectDto.getFood_name()+collectDto.getUid());*/
            int result = collectDao.save(collectDto);
            if(result==1){
                return ResultVo.setOK("收藏成功");
            }else{
                return ResultVo.setERROR("收藏失败");
            }

        }else{

            return ResultVo.setERROR("请先登录");
        }

    }


}
