package com.example.demo.pojo.common.s3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dongxu
 * @description 存储属性类
 * @create 2023-06-21 下午2:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "s3")
public class StorageProperty {
    private String url;
    private String accessKey;
    private String secretKey;
//    private long callTimeOut = 60000;
//    private long readTimeOut = 300000;
}
