package com.example.demo.service.article;

import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.modle.Article;
import com.example.demo.pojo.result.ResultBean;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章内容检查
 *
 * @author dongxu
 * @create 2023-08-13 下午4:59
 */
@Component
public class ContentReviewHandler implements ReviewHandler {

    @Resource
    private ImageReviewHandler imageReviewHandler;

    /**
     * 敏感词库
     */
    private static final List<String> SENSITIVE_WORDS = Lists.newArrayList(
            "SB", "死"
    );

    @Override
    public ResultBean<Boolean> review(Article article) {

        boolean anyMatch = SENSITIVE_WORDS.stream().anyMatch(x -> article.getInfo().contains(x));

        if (anyMatch) {
            throw new BusinessException("存在违禁词");
        }

        return imageReviewHandler.review(article);
    }
}
