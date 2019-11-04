package com.self.kitchen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class History {
    private int id;
    private String foodName;
    private String foodImg;
    @TableField("fId")
    private int fId;
    private int uid;


}
