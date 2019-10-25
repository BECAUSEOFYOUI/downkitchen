package com.self.kitchen.dto;

import com.self.kitchen.entity.FoodsType;
import lombok.Data;

;import java.util.List;

@Data
public class ChildType {
    private int id;
    private String name;
    private List<FoodsType> childTypes;
}
