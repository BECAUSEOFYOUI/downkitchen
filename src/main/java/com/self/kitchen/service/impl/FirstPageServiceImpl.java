package com.self.kitchen.service.impl;

import com.self.kitchen.dao.FirstPageDao;
import com.self.kitchen.entity.Food;
import com.self.kitchen.service.FirstPageService;
import com.self.kitchen.utils.FormatTimeUtils;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FirstPageServiceImpl implements FirstPageService {
    @Autowired
    FirstPageDao firstPageDao;

    @Override
    public ResultVo selectDay() {

        Date date = new Date();
        int time = FormatTimeUtils.getTime(date);
        List<Food> foodDay =null;
       if(time>=5&&time<10){
           foodDay = firstPageDao.selectDay(1);
       }else if(time>=10&&time<15){
           foodDay = firstPageDao.selectDay(2);
       }else if(time>=15&&time<22){
           foodDay = firstPageDao.selectDay(3);
       }else if(time>=22&&time<=24||(time>=0&&time<5)){
           foodDay = firstPageDao.selectDay(4);
       }
        return ResultVo.setOK(foodDay);
    }

    @Override
    public ResultVo quickFoods() {
        List<Food> quickFood=firstPageDao.quickFoods();
        return ResultVo.setOK(quickFood);
    }

    @Override
    public ResultVo hotFood() {
        List<Food> hotFood = firstPageDao.hotFood();
        return ResultVo.setOK(hotFood);
    }

    @Override
    public ResultVo hotFoods() {
        List<Food> hotFoods = firstPageDao.hotFoods();
        return ResultVo.setOK(hotFoods);
    }

}
