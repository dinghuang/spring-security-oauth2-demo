package org.springframework.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerAuthenticationManager implements AuthenticationManager {

    @Autowired
    private CustomerAuthenticationProviderConfiguration customerAuthenticationProviderConfiguration;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return customerAuthenticationProviderConfiguration.authenticate(authentication);
    }
}
