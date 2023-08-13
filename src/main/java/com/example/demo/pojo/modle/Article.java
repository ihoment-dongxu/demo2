package com.example.demo.pojo.modle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 文章
 *
 * @author dongxu
 * @create 2023-08-13 下午4:49
 */
@Data
public class Article {
    /**
     * 标题
     */
    @NotBlank
    private String title;
    /**
     * 作者
     */
    @NotBlank
    private String author;
    /**
     * 内容
     */
    @NotBlank
    private String info;
    /**
     * 主图
     */
    @NotBlank
    private String image;
}
