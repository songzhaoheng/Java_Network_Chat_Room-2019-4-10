package com.mapper;

import com.userInfo.UserInfo;

/*
* 登录信息接口
* */
public interface LoginInterface {

    //判断是否存在该用户
    public UserInfo getUserInfoByUsername(String username);
}
