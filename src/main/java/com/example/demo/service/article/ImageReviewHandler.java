package com.example.demo.service.article;

import com.example.demo.exception.BusinessException;
import com.example.demo.pojo.modle.Article;
import com.example.demo.pojo.result.ResultBean;
import org.springframework.stereotype.Component;

/**
 * 文章图片审核
 * @author dongxu
 * @create 2023-08-13 下午5:05
 */
@Component
public class ImageReviewHandler implements ReviewHandler{

    @Override
    public ResultBean<Boolean> review(Article article) {
        if (article.getImage().contains("yellow")) {
            throw new BusinessException("违规图片");
        }
        return ResultBean.ok(true);
    }

}
