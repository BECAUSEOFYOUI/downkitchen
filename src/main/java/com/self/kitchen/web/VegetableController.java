package com.self.kitchen.web;


import com.self.kitchen.service.VegetableService;
import com.self.kitchen.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiImplicitParam(name = "USERTOKEN",value = "用户私密令牌",required = true,dataType = "string")

    public ResultVo caiLanZi(String USERTOKEN) {

        return vegetableService.selectVegetable(USERTOKEN);
    }

    @GetMapping("/api/user/deleteByFid.do")
    @ApiOperation(value="实现菜篮子按菜品id删除",notes = "实现菜篮子按菜品id删除")
    @ResponseBody
    public ResultVo deleteByFid(int fid,String USERTOKEN) {
        System.out.println(fid);
        return vegetableService.deleteByFid(fid,USERTOKEN);
    }

    @GetMapping("/api/user/deleteByUid.do")
    @ApiOperation(value="实现菜篮子按用户id删除",notes = "实现菜篮子按用户id删除")
    @ResponseBody
    public ResultVo deleteByUid(int uid,String USERTOKEN) {
        System.out.println(uid);
        return vegetableService.deleteByUid(uid);
    }

    @GetMapping("/api/user/addByFid.do")
    @ApiOperation(value="实现菜篮子按菜品id添加",notes = "实现菜篮子按菜品id添加")
    @ResponseBody
    public ResultVo addByFid(int fid) {
        System.out.println(fid);
        return vegetableService.addByFid(fid);
    }
}
