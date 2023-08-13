package com.example.demo.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 多种支付适配器选取服务
 *
 * @author dongxu
 * @create 2023-08-13 下午4:31
 */
@Service
public class PayForMuitServiceAdapter {

    @Autowired
    private List<PayAdapter> adapters;

    public String payForAdapter(String orderNumber, BigDecimal amount, String adapter) {
        for (PayAdapter payAdapter : adapters) {
            if (payAdapter.support(adapter)) {
                return payAdapter.pay(orderNumber, amount, adapter);
            }
        }
        return "不支持当前支付方式：" + adapter;
    }
}
