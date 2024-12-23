package com.example.demo.config;

import com.example.demo.aop.RpcCaller;
import com.example.demo.utils.SimpleRpcCaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {
    @Bean
    public RpcCaller rpcCaller() {
        return new SimpleRpcCaller();
    }
}