package com.self.kitchen.entity;

import lombok.Data;

@Data
public class Step {
    private Integer id;
    private String img;
    private String stepDesc;
    private int steps;
    private int fid;
}
