package com.example.demo.service.article;

import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.modle.Article;
import com.example.demo.pojo.result.ResultBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 文章基础审核
 *
 * @author dongxu
 * @create 2023-08-13 下午4:55
 */
@Primary
@Component
public class BaseReviewHandler implements ReviewHandler {

    @Resource
    private ContentReviewHandler contentReviewHandler;

    private static final Integer MAX_TITLE_LENGTH = 10;
    private static final Integer MAX_INFO_LENGTH = 30;

    @Override
    public ResultBean<Boolean> review(Article article) {

        assert article != null;

        if (article.getTitle().length() > MAX_TITLE_LENGTH) {
            throw new BusinessException("标题不能超过10");
        }

        if (article.getInfo().length() > MAX_INFO_LENGTH) {
            throw new BusinessException("内容不能超过30");
        }

        return contentReviewHandler.review(article);
    }
}
