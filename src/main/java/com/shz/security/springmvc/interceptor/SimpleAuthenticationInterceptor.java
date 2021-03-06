package com.shz.security.springmvc.interceptor;

import com.shz.security.springmvc.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这个方法中校验用户请求的url是否在用户的权限范围内
        //取出用户身份信息
        Object object = request.getSession().getAttribute(UserEntity.SESSION_USER_KEY);
        if (object == null) {
            //没有认证，提示登录
            writeContent(response,"请登录");
        }
        UserEntity userEntity = (UserEntity) object;
        //请求的url
        String requestURI = request.getRequestURI();
        if (userEntity.getAuthorities().contains("p1") && requestURI.contains("/r/r1")) {
            return true;
        }
        if (userEntity.getAuthorities().contains("p2") && requestURI.contains("/r/r2")) {
            return true;
        }
        writeContent(response,"没有权限，拒绝访问");
        return false;
    }

    //响应信息给客户端
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
