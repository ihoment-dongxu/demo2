package com.example.demo.service.pay;

import java.math.BigDecimal;

/**
 * 支付适配器
 *
 * @author dongxu
 * @create 2023-08-13 下午4:16
 */
public interface PayAdapter {
    /**
     * 是否支持
     *
     * @param adapter 适配器
     * @return true 支持
     */
    boolean support(String adapter);

    /**
     * 支付
     *
     * @param orderNumber 订单
     * @param amount      金额
     * @param adapter     适配器
     * @return 支付结果
     */
    String pay(String orderNumber, BigDecimal amount, String adapter);
}
