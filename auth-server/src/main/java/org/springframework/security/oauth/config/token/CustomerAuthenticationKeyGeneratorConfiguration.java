package org.springframework.security.oauth.config.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@Configuration
public class CustomerAuthenticationKeyGeneratorConfiguration implements AuthenticationKeyGenerator {


    @Override
    public String extractKey(OAuth2Authentication authentication) {
        //自定义token生成，可以用来实现ip控制登录用户只能有一个，顶掉登录
        return null;
    }
}
