package org.springframework.security.oauth.config.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth.properties.Oauth2Properties;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerJwtStoreConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private Oauth2Properties oauth2Properties;
    @Autowired
    private CustomerAuthenticationKeyGeneratorConfiguration customerAuthenticationKeyGeneratorConfiguration;
    @Autowired
    private CustomerJwtAccessTokenConverterConfiguration customerJwtAccessTokenConverterConfiguration;

    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(customerAuthenticationKeyGeneratorConfiguration);
        return redisTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        customerJwtAccessTokenConverterConfiguration.setSigningKey(oauth2Properties.getJwtSigningKey());
        return customerJwtAccessTokenConverterConfiguration;
    }
}
