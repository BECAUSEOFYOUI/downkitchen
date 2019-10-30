package com.self.kitchen.web;

import com.self.kitchen.entity.User;
import com.self.kitchen.service.VegetableService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VegetableController {

    @Autowired
    VegetableService vegetableService;

    @GetMapping("/api/user/caiLanZi.do")
    @ApiOperation(value="实现菜篮子按菜品查询",notes = "实现菜篮子按菜品查询")
    @ResponseBody
    public ResultVo caiLanZi(User user) {
        System.out.println(user);
        return vegetableService.selectVegetableByCaiPin(user);
    }
}
