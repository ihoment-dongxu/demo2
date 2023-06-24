package com.example.demo.controller;

import com.example.demo.config.MinioClientConfig;
import com.example.demo.pojo.result.ResultBean;
import com.example.demo.utils.MinioUtil;
import io.minio.MinioClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * @author dongxu
 * @create 2023-06-24 下午2:29
 */
@RestController
@RequestMapping(value = "/minio")
public class MinIoController {

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 是否成功
     */
    @PostMapping(value = "/uploadFile")
    public ResultBean uploadFile(@RequestBody MultipartFile multipartFile) {
        if (!checkClient()) {
            return ResultBean.fail("Minio 服务器连接失败");
        }

        ResultBean<Map<String, Object>> upload = MinioUtil.minioUpload(multipartFile, multipartFile.getOriginalFilename(), "file-bucket");
        assert upload != null;
        if (upload.getStatus() == ResultBean.SUCCESS) {
            return ResultBean.ok("上传成功！");
        } else {
            return ResultBean.fail("上传失败！");
        }
    }


    /**
     * 获取文件预览地址
     *
     * @param fileName 文件名
     * @return
     */
    @GetMapping(value = "/getRedFile")
    public ResultBean getRedFile(@RequestParam String fileName) {
        if (!checkClient()) {
            return ResultBean.fail("Minio 服务器连接失败");
        }

        String previewFileUrl = MinioUtil.getPreviewFileUrl("file-bucket", fileName);

        return ResultBean.ok(previewFileUrl);
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     * @return
     */
    @GetMapping(value = "/downloadFile")
    public String downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        if (!checkClient()) {
            return "Minio 服务器连接失败";
        }
        InputStream inputStream = MinioUtil.downloadFile("file-bucket", fileName, response);
        return inputStream == null ? "下载失败！" : "下载成功！";
    }

    /**
     * 删除文件
     *
     * @param fileName 文件路径
     * @return
     */
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam String fileName) {
        if (!checkClient()) {
            return "连接MinIO服务器失败";
        }
        boolean flag = MinioUtil.deleteBucketFile("file-bucket", fileName);
        return flag ? "删除成功" : "删除失败";
    }

    private boolean checkClient() {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        return minioClient != null;
    }
}
