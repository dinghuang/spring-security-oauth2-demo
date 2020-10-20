package org.springframework.security.oauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dinghuang123@gmail.com
 * @since 2020/10/20
 */
@ConfigurationProperties(prefix = "security.oauth2")
public class Oauth2Properties {

    private String jwtSigningKey;
    private String permitUrl;

    private Oauth2ClientProperties[] clients = {};

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public String getPermitUrl() {
        return permitUrl;
    }

    public void setPermitUrl(String permitUrl) {
        this.permitUrl = permitUrl;
    }

    public Oauth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(Oauth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
