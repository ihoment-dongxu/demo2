package com.example.demo.controller;

import com.example.demo.pojo.modle.Article;
import com.example.demo.pojo.result.ResultBean;
import com.example.demo.service.article.BaseReviewHandler;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 文章
 *
 * @author dongxu
 * @create 2023-08-13 下午4:51
 */
@RestController
@RequestMapping(value = "/dx/v1/article")
public class ArticleController {

    @Resource
    private BaseReviewHandler baseReviewHandler;

    @PostMapping(value = "/review")
    @ApiModelProperty(value = "审核")
    public ResultBean<Boolean> review(@Valid @RequestBody Article article) {
        return baseReviewHandler.review(article);
    }

}
