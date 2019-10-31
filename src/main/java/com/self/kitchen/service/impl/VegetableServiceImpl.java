package com.self.kitchen.service.impl;

import com.self.kitchen.dao.FoodDao;
import com.self.kitchen.dao.MariableDao;
import com.self.kitchen.dao.VegetableDao;
import com.self.kitchen.entity.Food;
import com.self.kitchen.entity.Mariable;
import com.self.kitchen.entity.User;
import com.self.kitchen.entity.Vegetable;
import com.self.kitchen.service.VegetableService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VegetableServiceImpl implements VegetableService {

    @Autowired
    FoodDao foodDao;

    @Autowired
    MariableDao mariableDao;

    @Autowired
    VegetableDao vegetableDao;

    @Override
    public ResultVo selectVegetableByCaiPin(User user) {

        Integer uid = user.getId();
        Vegetable vegetable = vegetableDao.selectByUid(uid);

        if(vegetable != null) {
            Integer[] fids = vegetable.getFid();
            System.out.println(fids);

            List<Mariable> mariables = null;
            List<String> list = null;
            HashMap<List<String>, List<Mariable>> map = new HashMap<>();

            for(Integer fid:fids) {
                Food food = foodDao.selectFoodByFid(fid);//获取食物
                String foodName = food.getFoodName();
                String foodImg = food.getFoodImg();
                list.add(foodName);
                list.add(foodImg);

                mariables = mariableDao.selectMariableByFoodId(fid);
                map.put(list, mariables);
            }

            return ResultVo.setOK(map);
        }

        return ResultVo.setERROR();
    }

    @Override
    public ResultVo deleteByFid(int fid) {
        return vegetableDao.deleteByFid(fid);
    }

    @Override
    public ResultVo deleteByUid(int uid) {
        return vegetableDao.deleteByUid(uid);
    }
}
