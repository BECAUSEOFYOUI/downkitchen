package com.self.kitchen.dao;

import com.self.kitchen.dto.VegetableDto;
import com.self.kitchen.entity.Vegetable;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VegetableDao {

    @Select("select * from cai_lan_zi where uid=#{uid}")
    @ResultType(Vegetable.class)
    Vegetable selectByUid(Integer uid);


    @Select("delete from cai_lan_zi c,cailan_food cf where fid=#{fid}")
    @ResultType(Integer.class)
    int deleteByFid(Integer fid,Integer uid);

    @Select("delete from cai_lan_zi where uid=#{uid}")
    @ResultType(Integer.class)
    int deleteByUid(int uid);

    @Select("insert into cai_lan_zi(fid) values(#{fid}))")
    @ResultType(Integer.class)
    int addByFid(int fid);

    @Select("select fid from cailan_food where cid=#{cid}")
    List<Integer> selectFoodId(int cid);
}
