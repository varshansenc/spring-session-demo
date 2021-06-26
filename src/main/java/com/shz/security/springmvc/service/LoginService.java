package com.shz.security.springmvc.service;

import com.shz.security.springmvc.query.LoginQuery;
import com.shz.security.springmvc.entity.UserEntity;

public interface LoginService {
    /**
     * 用户认证
     * @param loginQuery 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    UserEntity login(LoginQuery loginQuery);
}
