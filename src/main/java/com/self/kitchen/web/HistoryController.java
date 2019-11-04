package com.self.kitchen.web;

import com.self.kitchen.entity.Food;
import com.self.kitchen.entity.History;
import com.self.kitchen.service.HistoryService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "历史记录",tags = "历史记录")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @ApiOperation(value = "点菜谱时会加入历史记录里")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "历史记录的食物id",required = true,dataType="int"),
            @ApiImplicitParam(name="USERTOKEN",value = "用户令牌",required = true,dataType = "string"),
    }
    )
    @PostMapping("/api/history/addHistory.do")
    public ResultVo addHistory(Integer id,String USERTOKEN) {

        return historyService.save(id,USERTOKEN);
    }

    @ApiOperation(value = "展示历史记录")
    @GetMapping("/api/history/list.do")
    @ApiImplicitParam(name = "USERTOKEN",value = "用户私密令牌",required = true,dataType = "string")
    public ResultVo listHistory(String USERTOKEN) {

        return historyService.list(USERTOKEN);
    }

    @ApiOperation(value = "删除历史记录")
    @DeleteMapping("/api/history/del.do")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "对应历史记录表的id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "USERTOKEN",value = "用户私密令牌",required = true,dataType = "string")
    })
    public ResultVo delHistory(int id,String USERTOKEN) {

        return historyService.removeById(id,USERTOKEN);
    }
}
