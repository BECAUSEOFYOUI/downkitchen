package com.self.kitchen.service.impl;


import com.self.kitchen.dao.NewsDao;
import com.self.kitchen.entity.Img;
import com.self.kitchen.entity.KitchenStories;
import com.self.kitchen.entity.SoryType;
import com.self.kitchen.service.NewsService;
import com.self.kitchen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDao newsDao;
    @Override
    public     List<KitchenStories>  selectCategory(Integer num) {

        List<KitchenStories> list = newsDao.selectCategory(num);
        System.out.println("起步");
        for (KitchenStories kitchenStories : list) {
          int  id=kitchenStories.getId();
          List<Img> imgs = newsDao.selectImg(id);
//            for (Img img : imgs) {
//                img.setImgs("http://localhost:8081/images/"+img.getImgs());
//            }
//
//
//            System.out.println(imgs);
           kitchenStories.setImgs(imgs);

        }
        System.out.println(list);
        return list;
    }
    @Override
    public ResultVo selectHotTopic(Integer num) {
        KitchenStories kitchenStories = newsDao.selectHotTopic(num);
        return ResultVo.setOK(kitchenStories);
    }

    @Override
    public  List<SoryType> list() {
        List<SoryType> list = newsDao.list();
        return list;
    }

    @Override
    public KitchenStories selectDetails(Integer id) {
        return newsDao.selectDetails(id);
    }


}
