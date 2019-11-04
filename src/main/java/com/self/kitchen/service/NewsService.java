package com.self.kitchen.service;

import com.self.kitchen.entity.KitchenStories;
import com.self.kitchen.entity.SoryType;
import com.self.kitchen.vo.ResultVo;


import java.util.List;

public interface NewsService {
    List<KitchenStories> selectCategory(Integer num);





    ResultVo selectHotTopic(Integer valueOf);

    List<SoryType> list();

    KitchenStories selectDetails(Integer id);
}
