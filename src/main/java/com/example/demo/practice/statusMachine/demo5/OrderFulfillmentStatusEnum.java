package com.example.demo.practice.statusMachine.demo5;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单发货状态
 *
 * @Author: luo jie
 * @Date: 2022/6/6 3:38 PM
 */
@Getter
@AllArgsConstructor
public enum OrderFulfillmentStatusEnum {

    NOT_SHIPPED(0, "未发货"),

    PARTIAL_SHIPPED(1, "部分发货"),

    ALL_SHIPPED(2, "全部发货");

    private int code;

    private String desc;

}
