package com.zyk.weinxin.domain;

import lombok.Data;

import java.util.List;

@Data
public class MaterialList {
    private List<Item> item;
    private int total_count;
    private int item_count;
}
