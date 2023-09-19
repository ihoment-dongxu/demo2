package com.example.demo.pojo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券类型枚举
 *
 * @author dongxu
 * @create 2023-08-23 下午10:27
 */
@AllArgsConstructor
@Getter
public enum CouponTypeEnum {
    /**
     * 普通券
     */
    NORMAL,
    /**
     * 新人券
     */
    NEW_COMER,
    /**
     * 单品券
     */
    SINGLE_PRODUCT
}
