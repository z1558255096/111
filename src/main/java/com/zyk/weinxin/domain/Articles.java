package com.zyk.weinxin.domain;

import lombok.Data;

@Data
public class Articles {
    private String title;
    private String author;
    private String digest;
    private String content;
    private String content_source_url;
    private String thumb_media_id;
    private Integer show_cover_pic=0;
    private Integer need_open_comment=0;
    private Integer only_fans_can_comment=0;
}
