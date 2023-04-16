package com.example.demo.singleton;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 单例模式实现RestTemplate
 *
 * @author dongxu
 */
public enum RestTemplateSingleton {

    SINGLETON;

    private final RestTemplate restTemplate;

    public RestTemplate getInstance() {
        return restTemplate;
    }

    RestTemplateSingleton() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(30000);
        restTemplate = new RestTemplate(factory);
        FormHttpMessageConverter fastConverter = new FormHttpMessageConverter();
        restTemplate.getMessageConverters().add(fastConverter);
    }
}
