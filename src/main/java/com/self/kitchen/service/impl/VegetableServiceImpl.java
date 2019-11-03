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
                vegetableDto.setId(cid);
                Integer fid = foodsId.get(i);
                /*System.out.println("fid"+fid);*/
                FoodDto foods = foodDao.selectFoods(fid);
                System.out.println("img"+foods.getFoodName());
                vegetableDto.setFoodImg(foods.getFoodImg());
                vegetableDto.setFoodName(foods.getFoodName());
                vegetableDto.setFid(fid);
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
            System.out.println("delete"+USERTOKEN);
            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            System.out.println("delete"+username);
            Integer uid = collectDao.selectUserId(username);
            System.out.println(uid);
            int id = vegetableDao.selectCidByUid(uid);
            System.out.println("id"+id+"fid"+fid);
            int result = vegetableDao.deleteByFid(id,fid);
            System.out.println("result"+result);
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
    public ResultVo deleteByUid(String USERTOKEN) {

        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);

            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            Integer uid = collectDao.selectUserId(username);

            int id = vegetableDao.selectCidByUid(uid);
            int r = vegetableDao.deleteByUid(id);
            if(r==1){
                return ResultVo.setOK("删除成功");
            }else{
                return ResultVo.setERROR("删除失败");
            }
        }else{
            return ResultVo.setERROR("请先登录");
        }
    }

    @Override
    public ResultVo addByFid(int fid,String USERTOKEN) {

        if(USERTOKEN!=null&&!USERTOKEN.equals("")){
            String userToken = redisTemplate.opsForValue().get(USERTOKEN);

            String username = redisTemplate.opsForValue().get("ACCOUNT"+userToken);
            Integer uid = collectDao.selectUserId(username);
            System.out.println("uid"+uid);
            int r = vegetableDao.selectCotantUid(uid);
            System.out.println("r"+r);
            int r1=0;
            int r2=0;
            if(r==1){
                int cid = vegetableDao.selectCidByUid(uid);
                r2 = vegetableDao.insertCF(cid,fid);
                r1=1;
            }else{
                r1 = vegetableDao.insertCaiLan(uid);
                int cid = vegetableDao.selectCidByUid(uid);
                r2 = vegetableDao.insertCF(cid,fid);
            }

            System.out.println("r1"+r1+"r2"+r2);
            if(r1==r2){
                return ResultVo.setOK("添加菜篮子成功");
            }else{
                return ResultVo.setERROR("添加菜篮子失败");
            }

        }else{
            return ResultVo.setERROR("请先登录");
        }

    }
}
