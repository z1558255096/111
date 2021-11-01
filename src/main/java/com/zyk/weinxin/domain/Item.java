package com.zyk.weinxin.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private String media_id;
    private String name;
    private long update_time;
    private String url;
    private List<String> tags;
}
