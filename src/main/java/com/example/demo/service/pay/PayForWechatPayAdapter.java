package com.example.demo.service.pay;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 微信支付
 *
 * @author dongxu
 * @create 2023-08-13 下午4:19
 */
@Component(value = PayForWechatPayAdapter.PAY_KIND)
public class PayForWechatPayAdapter implements PayAdapter {

    public static final String PAY_KIND = "WECHAT_PAY";

    @Override
    public boolean support(String adapter) {
        return SpringUtil.getBean(adapter) instanceof PayForWechatPayAdapter;
    }

    @Override
    public String pay(String orderNumber, BigDecimal amount, String adapter) {
        // 调用微信的Api
        return "订单号："+orderNumber+"，支付金额："+amount+"，支付方式：微信";
    }
}
