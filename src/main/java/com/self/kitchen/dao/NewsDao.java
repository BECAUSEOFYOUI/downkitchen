package com.self.kitchen.dao;

import com.self.kitchen.entity.Img;
import com.self.kitchen.entity.KitchenStories;
import com.self.kitchen.entity.SoryType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsDao {
    @Select("select * from kitchen.story where id=#{num}")
    @ResultType(KitchenStories.class)
    KitchenStories selectHotTopic(Integer num);


    @Select("select s.headline,s.id from story s,storytype t where s.sid=t.id and t.id=#{num}")
    @ResultType(KitchenStories.class)
    List<KitchenStories> selectCategory(Integer num);
    @Select("select imgs from story_img where sid=#{id}")
    @ResultType(Img.class)
    List<Img>  selectImg(Integer id);
    @Select("select *from storytype")
    List<SoryType> list();
    @Select("select text from story where id=#{id}")
    KitchenStories selectDetails(Integer id);
}
