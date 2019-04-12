package com.userInfo;

import lombok.Getter;
import lombok.Setter;

/*
* UserInfoJavaBean类
* */
@Getter
@Setter
public class UserInfo {

    private Integer userid;
    private String username;
    private String userpass;

    public UserInfo() {
        super();
    }

    public UserInfo(Integer userid, String username, String userpass) {
        this.userid = userid;
        this.username = username;
        this.userpass = userpass;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                '}';
    }
}
