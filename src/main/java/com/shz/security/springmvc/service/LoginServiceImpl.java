package com.shz.security.springmvc.service;

import com.shz.security.springmvc.query.LoginQuery;
import com.shz.security.springmvc.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service
public class LoginServiceImpl implements LoginService {
    // 用户信息
    private Map<String, UserEntity> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1"); // 这个p1我们人为让它和/r/r1对应
//        authorities1.add("p2"); // 这个p2我们人为让它和/r/r2对应
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2"); // 这个p2我们人为让它和/r/r2对应
        userMap.put("zhangsan",
                new UserEntity("1010","zhangsan","123","张三","133443", authorities1));
        userMap.put("lisi",
                new UserEntity("1011","lisi","456","李四","144553", authorities2));
    }

    /**
     * 用户认证，校验用户身份信息是否合法
     *
     * @param loginQuery 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    @Override
    public UserEntity login(LoginQuery loginQuery) {
        //校验参数是否为空
        if(loginQuery == null
            || StringUtils.isEmpty(loginQuery.getUsername())
            || StringUtils.isEmpty(loginQuery.getPassword())){
            throw new RuntimeException("账号和密码为空");
        }
        //根据账号去查询数据库,这里测试程序采用模拟方法
        UserEntity user = getUserDto(loginQuery.getUsername());
        //判断用户是否为空
        if (user == null) {
            throw new RuntimeException("查询不到该用户");
        }
        //校验密码
        if (!loginQuery.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        //认证通过，返回用户身份信息
        return user;
    }
    //根据账号查询用户信息
    private UserEntity getUserDto(String userName){
        return userMap.get(userName);
    }

}
