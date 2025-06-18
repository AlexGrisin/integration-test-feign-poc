package com.ford.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthRequestInterceptor implements RequestInterceptor {
    private final String authHeaderValue;

    public BasicAuthRequestInterceptor(String username, String password) {
        String credentials = username + ":" + password;
        this.authHeaderValue = "Basic " + Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", authHeaderValue);
    }
}
