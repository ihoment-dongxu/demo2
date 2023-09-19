package com.example.demo.pojo.dto.coupon;

import lombok.Data;

/**
 * 领取优惠券
 *
 * @author dongxu
 * @create 2023-08-23 下午10:33
 */
@Data
public class ReceiveCouponDTO {
    private Integer userId;
    private String site;
    private String couponCode;
}
