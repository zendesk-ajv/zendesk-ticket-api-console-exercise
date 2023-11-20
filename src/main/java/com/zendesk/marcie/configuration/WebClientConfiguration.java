package com.zendesk.marcie.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {



        @Value("${zendesk.account.url}")
        private String url;


        @Value("${zendesk.account.username}")
        private String username;

        @Value("${zendesk.account.password}")
        private String password;


    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.setBasicAuth(username, password);
                })
                .build();
    }

}
