package com.self.kitchen.dto;

import com.self.kitchen.entity.Mariable;
import lombok.Data;

import java.util.List;
@Data
public class VegetableDto {

    private String foodImg;
    private String foodName;
    private List<Mariable> mariables;
}
