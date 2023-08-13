package com.example.demo.service.article;

import com.example.demo.pojo.modle.Article;
import com.example.demo.pojo.result.ResultBean;

/**
 * 审核
 *
 * @author dongxu
 * @create 2023-08-13 下午4:52
 */
public interface ReviewHandler {
    /**
     * 审核
     *
     * @param article 文章
     * @return 结果
     */
    ResultBean<Boolean> review(Article article);
}
