package com.example.demo.pojo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 *
 * @author dongxu
 * @create 2023-06-21 下午6:00
 */
@Data
public final class PageResult<T> implements Serializable {

    private List<T> list;

    private Long total;

    /**
     * 防止反序列化错误
      */
    public PageResult() {
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }


}
