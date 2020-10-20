package org.springframework.security.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerAuthenticationProviderConfiguration implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //这里做自定义的用户校验,可以对用户进行继承扩展
        User user = new User((String) authentication.getPrincipal(), (String) authentication.getCredentials(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //这里可以通过继承AbstractAuthenticationToken进行扩展
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
        usernamePasswordAuthenticationToken.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
