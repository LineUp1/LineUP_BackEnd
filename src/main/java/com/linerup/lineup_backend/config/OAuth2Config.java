package com.linerup.lineup_backend.config;

import com.linerup.lineup_backend.security.oauth2.converter.CustomRequestEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : LineUP_BackEnd
 * @name : OAuth2Config
 * @date : 2023/08/31 12:01 AM
 * @modifyed : $
 **/
@Configuration
@RequiredArgsConstructor
public class OAuth2Config {

    private final JdbcTemplate jdbcTemplate;
    private final ClientRegistrationRepository clientRegistrationRepository;
    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
        return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        accessTokenResponseClient.setRequestEntityConverter(customRequestEntityConverter());
        return accessTokenResponseClient;
    }

    @Bean
    public CustomRequestEntityConverter customRequestEntityConverter(){
        return new CustomRequestEntityConverter();
    }
}
