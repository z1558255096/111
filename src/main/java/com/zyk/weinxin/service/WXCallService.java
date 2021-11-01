package com.zyk.weinxin.service;

public interface WXCallService {
    String getAccessToken();
    String getUUMediaId(String accessToken);
    String newDraft();
    String getMaterial(String mediaId,String accessToken);

}
