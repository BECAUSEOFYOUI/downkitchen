package com.self.kitchen.web;

import com.self.kitchen.utils.UUIDUtils;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@Api(value = "文件上传操作",tags = "文件上传操作")
public class UploadController {


    @PostMapping("/api/img/upload")
    @ResponseBody
    @ApiOperation(value = "文件上传",notes = "文件上传")
    public String uploadFile(MultipartFile headImg){
        System.out.println(headImg);
        //截取文件名字
        String oldName = headImg.getOriginalFilename();
        //截取文件后缀名
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        /*
            路径问题
            文件名称问题
         */
        //通过UUID生成一个新名字加上后缀名
        String newFileName = UUIDUtils.getUUID() + suffix;
        try {
            //调用此方法，完成上传功能

            headImg.transferTo(new File("D:/img/",newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //拼接图片地址
        String imgUrl= newFileName;
        System.out.println(imgUrl+"图片地址+++++++++++++++++++++++++++++++++++++++++++++++");
        //通过AJAX返回图片地址


        return imgUrl;
    }
}
