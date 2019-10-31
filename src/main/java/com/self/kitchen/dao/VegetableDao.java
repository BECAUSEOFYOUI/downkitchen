package com.self.kitchen.dao;

import com.self.kitchen.entity.Vegetable;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface VegetableDao {

    @Select("select * from cai_lan_zi where uid=#{uid}")
    @ResultType(Vegetable.class)
    Vegetable selectByUid(Integer uid);


    @Select("delete from cai_lan_zi where fid=#{fid}")
    @ResultType(Integer.class)
    ResultVo deleteByFid(int fid);

    @Select("delete from cai_lan_zi where uid=#{uid}")
    @ResultType(Integer.class)
    ResultVo deleteByUid(int uid);
}
