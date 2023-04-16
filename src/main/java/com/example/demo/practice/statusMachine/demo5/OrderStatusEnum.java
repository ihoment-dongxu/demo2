package com.example.demo.practice.statusMachine.demo5;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dongxu
 * @create 2023-04-14 下午4:00
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    WAIT_PAYMENT(1, "待付款"),

    WAIT_SHIPPING(2, "待发货"),

    SHIPPING(3, "部分发货"),

    ALL_SHIPPED(4, "全部发货"),

    CANCELED(5, "已取消"),

    REFUNDS(6, "退货退款");

    private Integer code;

    private String desc;
}
