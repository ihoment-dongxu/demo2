package com.example.demo.config;

import com.example.demo.pojo.common.s3.StorageProperty;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author dongxu
 * @descrion Minio配置类
 * @create 2023-06-24 下午2:01
 */
@Configuration
@Component
@Slf4j
public class MinioClientConfig {

    @Resource
    private StorageProperty storageProperty;

    private static MinioClient minioClient;

    @PostConstruct
    public void init() {
        try {
            minioClient = MinioClient.builder()
                    .endpoint(storageProperty.getUrl())
                    .credentials(storageProperty.getAccessKey(), storageProperty.getSecretKey())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化minio配置异常，{}", e.fillInStackTrace());
        }
    }

    /**
     * 获取minioClient
     *
     * @return minioClient
     */
    public static MinioClient getMinioClient() {
        return minioClient;
    }

    /**
     * 判断桶是否存在
     *
     * @param bucketName 桶名字
     * @return true or false
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取所有的桶
     *
     * @return 桶列表
     */
    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

}
