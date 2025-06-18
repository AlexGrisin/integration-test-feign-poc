package com.ford.config;

import feign.Feign;
import feign.RequestInterceptor;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class FeignFactory {
    public static <T> T buildClient(Class<T> clientClass, String baseUrl) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(clientClass, baseUrl);
    }

    public static <T> T buildClient(Class<T> clientClass, String baseUrl, RequestInterceptor interceptor) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(interceptor)
                .logger(new Slf4jLogger(clientClass))
                .logLevel(Logger.Level.BASIC)
                .target(clientClass, baseUrl);
    }
}
