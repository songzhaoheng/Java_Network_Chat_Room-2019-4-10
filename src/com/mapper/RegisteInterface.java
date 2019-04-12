package com.mapper;

import com.userInfo.UserInfo;
import com.userInfo.UserName;
import com.userInfo.UserPassName;

public interface RegisteInterface {

    //判断用户名是否存在
    public UserInfo getUserName(String user_name);

    //进行用户信息注册
    public boolean insertUserInfo(UserInfo userInfo);


}
