package com.userInfo;

import lombok.Getter;
import lombok.Setter;

/*
* 用户名类
* */
@Getter
@Setter
public class UserName {

    private Integer userid;
    private String username;

    public UserName() {
        super();
    }

    public UserName(Integer userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserName{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                '}';
    }
}
