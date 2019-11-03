package com.self.kitchen.dao;

import com.self.kitchen.dto.VegetableDto;
import com.self.kitchen.entity.Vegetable;
import com.self.kitchen.vo.ResultVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VegetableDao {

    @Select("select * from cai_lan_zi where uid=#{uid}")
    @ResultType(Vegetable.class)
    Vegetable selectByUid(Integer uid);


    @Delete("delete from cailan_food  where fid=#{fid} and cid=#{id}")

    int deleteByFid(Integer id,Integer fid);

    @Delete("delete from cailan_food where cid=#{id}")
    @ResultType(Integer.class)
    int deleteByUid(int id);

    @Select("insert into cai_lan_zi(fid) values(#{fid}))")
    @ResultType(Integer.class)
    int addByFid(int fid);

    @Select("select fid from cailan_food where cid=#{cid}")
    List<Integer> selectFoodId(int cid);

    @Select("select id from cai_lan_zi where uid=#{uid}")
    @ResultType(Integer.class)
    int selectCidByUid(Integer uid);

    @Insert("insert into cai_lan_zi (uid) values(#{uid})")
    int insertCaiLan(Integer uid);

    @Insert("insert into cailan_food (cid,fid) values(#{cid},#{fid})")
    int insertCF(int cid, int fid);

    @Select("select count(*) from cai_lan_zi where uid=#{uid}")
    int selectCotantUid(Integer uid);
}
