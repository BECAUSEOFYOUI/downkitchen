package com.self.kitchen.web;

import com.self.kitchen.entity.Collect;
import com.self.kitchen.entity.Food;
import com.self.kitchen.service.CollectService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(value = "收藏菜谱",tags = "收藏菜谱")
public class CollectController {

    @Autowired
    CollectService collectService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation(value = "收藏这个菜谱")
    @PostMapping("/api/collect/collect.do")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "收藏的食物id",required = true,dataType="int"),
            @ApiImplicitParam(name="USERTOKEN",value = "用户令牌",required = true,dataType = "string"),
        }
    )
    public ResultVo collectFood(Integer id,String USERTOKEN) {

        return collectService.collectFood(id,USERTOKEN);
    }

    @ApiOperation(value = "展示收藏的菜谱")
    @GetMapping("/api/collect/list")
    @ApiImplicitParam(name = "USERTOKEN",value = "用户私密令牌",required = true,dataType = "string")
    public ResultVo listCollect(String USERTOKEN) {

        return collectService.listCollect(USERTOKEN);
    }

    @ApiOperation(value = "删除收藏的菜谱")
    @DeleteMapping("/api/collect/delById.do")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "对应收藏表的id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "USERTOKEN",value = "用户私密令牌",required = true,dataType = "string")
    })
    public ResultVo del(int id,String USERTOKEN) {

        return collectService.removeById(id,USERTOKEN);
    }

}
