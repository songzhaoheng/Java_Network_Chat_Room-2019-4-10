package com.mapper;

import com.userInfo.UserInfo;
import org.apache.ibatis.annotations.Param;

/*查询接口*/
public interface AlterInterface {

    //查询用户名和密码是否一致
    public UserInfo selectByName(String username);

    //用户密码修改
    public boolean updateByPass(@Param("username")String username, @Param("userpass")String userpass);
}
