package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author dongxu
 */
@EnableRetry
@EnableScheduling
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {"com.example.demo"}
)
public class DemoApplication {

    /**
     * 设置系统时区
     */
    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC+8"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
