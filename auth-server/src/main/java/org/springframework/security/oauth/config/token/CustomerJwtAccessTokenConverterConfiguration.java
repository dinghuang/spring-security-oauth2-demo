package org.springframework.security.oauth.config.token;

import io.jsonwebtoken.JwtHandlerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerJwtAccessTokenConverterConfiguration extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication){
       //可以实现jwt的token放一些用户信息
        return super.enhance(oAuth2AccessToken,oAuth2Authentication);
    }
}
