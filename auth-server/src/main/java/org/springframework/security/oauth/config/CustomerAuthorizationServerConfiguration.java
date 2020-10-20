package org.springframework.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth.properties.Oauth2ClientProperties;
import org.springframework.security.oauth.properties.Oauth2Properties;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
@EnableAuthorizationServer
public class CustomerAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private CustomerAuthenticationManager customerAuthenticationManager;
    @Autowired
    private Oauth2Properties oauth2Properties;
    @Autowired
    private CustomerWebResponseExceptionTranslator customerWebResponseExceptionTranslator;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) {
        authorizationServerEndpointsConfigurer.tokenEnhancer(jwtAccessTokenConverter)
                .tokenStore(tokenStore)
                .authenticationManager(customerAuthenticationManager)
                .exceptionTranslator(customerWebResponseExceptionTranslator);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        authorizationServerSecurityConfigurer.tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = clientDetailsServiceConfigurer.inMemory();
        for (Oauth2ClientProperties oauth2ClientProperties : oauth2Properties.getClients()) {
            inMemoryClientDetailsServiceBuilder.withClient(oauth2ClientProperties.getClientId())
                    .secret(bCryptPasswordEncoder().encode(oauth2ClientProperties.getClientSecret()))
                    .accessTokenValiditySeconds(oauth2ClientProperties.getAccessTokenValiditySeconds())
                    .refreshTokenValiditySeconds(oauth2ClientProperties.getRefreshTokenValiditySeconds())
                    .authorizedGrantTypes("refresh_token", "password", "authorization_code")
                    .scopes(oauth2ClientProperties.getScopes());
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
