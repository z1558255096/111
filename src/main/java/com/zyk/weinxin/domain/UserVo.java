package com.zyk.weinxin.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {
  private   List<User> list;
    private  Integer type;
}
