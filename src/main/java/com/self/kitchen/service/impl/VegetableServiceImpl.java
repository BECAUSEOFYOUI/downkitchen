package com.self.kitchen.service.impl;

import com.self.kitchen.dao.CollectDao;
import com.self.kitchen.dao.FoodDao;
import com.self.kitchen.dao.MariableDao;
import com.self.kitchen.dao.VegetableDao;

import com.self.kitchen.dto.FoodDto;
import com.self.kitchen.dto.VegetableDto;
import com.self.kitchen.entity.*;
import com.self.kitchen.service.VegetableService;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import java.util.ArrayList;
import java.util.List;

@Service
public class VegetableServiceImpl implements VegetableService {

    @Autowired
    FoodDao foodDao;

    @Autowired
    MariableDao mariableDao;

    @Autowired
    VegetableDao vegetableDao;
    @Autowired
    private CollectDao collectDao;
    @Resource

    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo selectVegetable(String USERTOKEN) {

        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            /*System.out.println(USERTOKEN);*/
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);
            /*System.out.println("Vegetable"+userToken);*/
            String username =redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            /*System.out.println(username);*/
            int uid = collectDao.selectUserId(username);
          /*  System.out.println(""+uid);*/
            Vegetable vegeTable = vegetableDao.selectByUid(uid);
            int cid =vegeTable.getId();
            /*System.out.println("cid"+cid);*/
            List<VegetableDto> vegetableDtos=new ArrayList<>();
            List<Integer> foodsId = vegetableDao.selectFoodId(cid);

            for(int i=0;i<foodsId.size();i++){
                VegetableDto vegetableDto=new VegetableDto();
                Integer fid = foodsId.get(i);
                /*System.out.println("fid"+fid);*/
                FoodDto foods = foodDao.selectFoods(fid);
                System.out.println("img"+foods.getFoodName());
                vegetableDto.setFoodImg(foods.getFoodImg());
                vegetableDto.setFoodName(foods.getFoodName());
                List<Mariable> mariables = foodDao.selectMaterial(fid);
               /* System.out.println(mariables);*/
                vegetableDto.setMariables(mariables);
                vegetableDtos.add(vegetableDto);
                /*System.out.println(1);
                System.out.println(vegetableDtos);*/
            }
            return ResultVo.setOK(vegetableDtos);
        }else{
            return ResultVo.setERROR("请先登录");
        }
    }

    @Override
    public ResultVo deleteByFid(int fid,String USERTOKEN) {

        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);

            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            Integer uid = collectDao.selectUserId(username);


            int result = vegetableDao.deleteByFid(fid,uid);
        }else{
            return ResultVo.setERROR("请先登录");
        }

        return ResultVo.setERROR();

    }

    @Override
    public ResultVo deleteByUid(int uid) {
        int result = vegetableDao.deleteByUid(uid);
        if(result > 0) {
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }

    @Override
    public ResultVo addByFid(int fid) {
        int result = vegetableDao.addByFid(fid);
        if(result > 0) {
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }
}
