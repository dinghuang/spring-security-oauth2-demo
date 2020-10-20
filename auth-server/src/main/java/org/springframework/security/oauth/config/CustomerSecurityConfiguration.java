package org.springframework.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.filter.CustomerOauth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
@EnableWebSecurity
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerAuthenticationManager customerAuthenticationManager;
    @Autowired
    private CustomerAuthenticationEntryPointConfiguration customerAuthenticationEntryPointConfiguration;
    @Autowired
    private CustomerOauth2AuthenticationProcessingFilter customerOauth2AuthenticationProcessingFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().addFilterBefore(customerOauth2AuthenticationProcessingFilter, AnonymousAuthenticationFilter.class)
                .csrf().disable().exceptionHandling().authenticationEntryPoint(customerAuthenticationEntryPointConfiguration);
    }

    @Override
    public AuthenticationManager authenticationManager(){
        return customerAuthenticationManager;
    }

}
