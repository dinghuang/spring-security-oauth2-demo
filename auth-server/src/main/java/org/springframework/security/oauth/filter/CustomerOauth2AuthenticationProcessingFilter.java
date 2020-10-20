package org.springframework.security.oauth.filter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth.config.CustomerAuthenticationProviderConfiguration;
import org.springframework.security.oauth.properties.Oauth2Properties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Component
public class CustomerOauth2AuthenticationProcessingFilter implements Filter, InitializingBean {

    @Autowired
    private Oauth2Properties oauth2Properties;
    @Autowired
    private CustomerAuthenticationProviderConfiguration customerAuthenticationProviderConfiguration;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //白名单过滤
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String path : oauth2Properties.getPermitUrl().split(",")) {
            if (antPathMatcher.match(path, httpServletRequest.getPathInfo())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        //自定义验证
        Authentication authentication = getFromRequest(httpServletRequest);
        if (authentication == null) {
            SecurityContextHolder.clearContext();
        } else {
            SecurityContextHolder.getContext().setAuthentication(customerAuthenticationProviderConfiguration.authenticate(authentication));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Authentication getFromRequest(HttpServletRequest httpServletRequest) {
        //todo 这里可以自己去实现
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
