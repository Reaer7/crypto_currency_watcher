package com.gmail.brutskiart.watcher.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptorWithToken() {
        return requestTemplate -> requestTemplate.header(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
                HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE
        );
    }
}
