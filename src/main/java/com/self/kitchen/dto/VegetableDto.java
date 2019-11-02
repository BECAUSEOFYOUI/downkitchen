package com.self.kitchen.dto;

import com.self.kitchen.entity.Mariable;
import lombok.Data;

import java.util.List;
@Data
public class VegetableDto {
    private int id;
    private String foodImg;
    private String foodName;
    private int fid;
    private List<Mariable> mariables;
}
