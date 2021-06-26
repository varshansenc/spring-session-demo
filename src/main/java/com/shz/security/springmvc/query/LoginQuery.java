package com.shz.security.springmvc.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginQuery implements Serializable {
    //认证请求参数，账号、密码。。
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
