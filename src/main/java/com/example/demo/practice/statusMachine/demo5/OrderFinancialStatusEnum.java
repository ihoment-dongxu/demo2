package com.example.demo.practice.statusMachine.demo5;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单支付状态
 *
 * @Author: luo jie
 * @Date: 2022/6/6 3:23 PM
 */
@Getter
@AllArgsConstructor
public enum OrderFinancialStatusEnum {

    WAIT_PAYMENT(1, "待支付"),

    PAYMENT_ING(2, "支付中"),

    PAYMENT_FAILED(3, "支付失败"),

    PAYMENT_ED(4, "已支付"),

    PARTIAL_REFUND(5, "部分退款"),

    FULL_REFUND(6, "已退款");

    private int code;

    private String desc;


}
