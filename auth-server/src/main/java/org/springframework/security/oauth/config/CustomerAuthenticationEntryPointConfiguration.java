package org.springframework.security.oauth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerAuthenticationEntryPointConfiguration implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常消息通知
        httpServletResponse.getWriter().write("暂无权限访问");
    }
}
