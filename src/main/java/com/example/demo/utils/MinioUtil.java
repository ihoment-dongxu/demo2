package com.example.demo.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.config.MinioClientConfig;
import com.example.demo.pojo.result.ResultBean;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Minio 工具类
 *
 * @author dongxu
 */
@Slf4j
@Component
public class MinioUtil {

    /**
     * Minio文件上传
     *
     * @param file       文件实体
     * @param fileName   修饰过的文件名 非源文件名
     * @param bucketName 所存文件夹（桶名）
     * @return
     */
    public static ResultBean<Map<String, Object>> minioUpload(MultipartFile file, String fileName, String bucketName) {
        ResultBean<Map<String, Object>> resultBean = new ResultBean();
        try {
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            // fileName为空，说明要使用源文件名上传
            if (fileName == null) {
                fileName = file.getOriginalFilename();
                assert fileName != null;
                fileName = fileName.replaceAll(" ", "_");
            }
            InputStream inputStream = file.getInputStream();
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(fileName)
                    .stream(inputStream, file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 检查存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return
     */
    public static boolean bucketExists(String bucketName) {
        boolean flag = false;
        try {
            flag = MinioClientConfig.bucketExists(bucketName);
            if (flag) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 获取文件流
     *
     * @param fileName   文件名
     * @param bucketName 桶名（文件夹）
     * @return
     */
    public static InputStream getFileInputStream(String fileName, String bucketName) {
        try {
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 创建桶
     *
     * @param bucketName 桶名字
     */
    public static void createBucketName(String bucketName) {
        try {
            if (StringUtils.isBlank(bucketName)) {
                return;
            }
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            boolean isExist = MinioClientConfig.bucketExists(bucketName);
            if (isExist) {
                log.info("Bucket {} already exists.", bucketName);
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param originalName 文件路径
     */
    public static InputStream downloadFile(String bucketName, String originalName, HttpServletResponse response) {
        try {
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            InputStream file = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(originalName).build());
            String filename = new String(originalName.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            if (StringUtils.isNotBlank(originalName)) {
                filename = originalName;
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = file.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除桶
     *
     * @param bucketName 桶名字
     */
    public static void deleteBucketName(String bucketName) {
        try {
            if (StringUtils.isBlank(bucketName)) {
                return;
            }
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            boolean isExist = MinioClientConfig.bucketExists(bucketName);
            if (isExist) {
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 删除桶下面所有文件
     *
     * @param bucketName 桶名字
     */
    public static void deleteBucketAllFile(String bucketName) {
        try {
            if (StringUtils.isBlank(bucketName)) {
                return;
            }
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                minioClient.deleteBucketEncryption(DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 删除桶某个文件
     *
     * @param bucketName 桶名
     * @param fileName   文件名
     * @return true or false
     */
    public static boolean deleteBucketFile(String bucketName, String fileName) {
        try {
            if (StringUtils.isBlank(bucketName) || StringUtils.isBlank(fileName)) {
                return false;
            }
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 根据文件路径得到预览文件绝对地址
     *
     * @param bucketName 桶名字
     * @param fileName   文件名
     * @return 绝对地址
     */
    public static String getPreviewFileUrl(String bucketName, String fileName) {
        try {
            MinioClient minioClient = MinioClientConfig.getMinioClient();
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    // 添加非空的 HTTP 方法
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
