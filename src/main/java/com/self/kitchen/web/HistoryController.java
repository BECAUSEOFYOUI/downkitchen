package com.self.kitchen.web;

import com.self.kitchen.entity.Food;
import com.self.kitchen.entity.History;
import com.self.kitchen.service.HistoryService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "历史记录",tags = "历史记录")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "点菜谱时会加入历史记录里")
    @PostMapping("/api/history/addHistory.do")
    public ResultVo addHistory(@RequestBody Food food) {
        History history = new History();
        history.setfId(food.getId());
        history.setFoodImg(food.getFoodImg());
        history.setFoodName(food.getFoodName());
        return historyService.save(history);
    }

    @ApiOperation(value = "展示历史记录")
    @GetMapping("/api/history/list.do")
    public ResultVo listHistory() {

        return historyService.list();
    }

    @ApiOperation(value = "删除历史记录")
    @DeleteMapping("/api/history/del.do")
    public ResultVo delHistory(int id) {

        return historyService.removeById(id);
    }
}
