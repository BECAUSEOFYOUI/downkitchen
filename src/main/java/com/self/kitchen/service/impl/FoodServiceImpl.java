package com.self.kitchen.service.impl;

import com.self.kitchen.dao.FoodTypeDao;
import com.self.kitchen.dao.TitleType;
import com.self.kitchen.dto.ChildType;
import com.self.kitchen.entity.FoodsType;
import com.self.kitchen.service.FoodService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodTypeDao foodDao;
    @Override
    public ResultVo selectFoodsType() {
        List<TitleType> foodsTitle = foodDao.selectFoodsTitle();
        List<ChildType> childTypeList = new ArrayList<>();
        for(int i=0;i<foodsTitle.size();i++){
           TitleType t =  foodsTitle.get(i);
            ChildType childType = new ChildType();
            childType.setId(t.getId());
            childType.setName(t.getName());
            List<FoodsType> foodsType = foodDao.selectFoodsType(t.getId());
            childType.setChildTypes(foodsType);
            childTypeList.add(childType);
        }
        return ResultVo.setOK(childTypeList);
    }
}
