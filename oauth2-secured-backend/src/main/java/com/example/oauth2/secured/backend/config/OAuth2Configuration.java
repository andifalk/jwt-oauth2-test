package com.example.oauth2.secured.backend.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@EnableResourceServer
@Configuration
public class OAuth2Configuration implements ResourceServerConfigurer {

    @Bean
    public JwtAccessTokenConverterConfigurer jwtAccessTokenConverterConfigurer() {
        return new MyJwtConfigurer();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/unrestricted").permitAll()
                .anyRequest().authenticated();
    }

    public static class MyJwtConfigurer implements JwtAccessTokenConverterConfigurer {

        /**
         * Constructor.
         */
        MyJwtConfigurer() {
        }

        @Override
        public void configure(JwtAccessTokenConverter converter) {
            MyUserAuthenticationConverter userAuthenticationConverter = new
                    MyUserAuthenticationConverter();
            DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
            defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
            converter.setAccessTokenConverter(defaultAccessTokenConverter);
        }
    }
}
