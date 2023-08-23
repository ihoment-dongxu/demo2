package com.example.demo.pojo.common;

import lombok.Data;

/**
 * web请求信息收集类
 *
 * @author dongxu
 * @create 2023-08-22 下午9:44
 */
@Data
public class RequestInfo {
    private Long uid;
    private String ip;
}
