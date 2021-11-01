package com.zyk.weinxin.domain;

import lombok.Data;

@Data
public class News_item {
    private String title;
    private String thumb_media_id;
    private int show_cover_pic;
    private String author;
    private String digest;
    private String content;
    private String url;
    private String content_source_url;
}
