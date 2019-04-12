package com.userInfo;

import lombok.Getter;
import lombok.Setter;

/*
* 用户名和密码
* */
@Setter
@Getter
public class UserPassName {

    private String username;
    private String userpass;

    public UserPassName() {
        super();
    }

    public UserPassName(String username, String userpass) {
        this.username = username;
        this.userpass = userpass;
    }

    @Override
    public String toString() {
        return "UserPassName{" +
                "username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                '}';
    }
}
