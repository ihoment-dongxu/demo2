package com.example.demo.service.pay;

import cn.hutool.extra.spring.SpringUtil;
import com.example.demo.context.OrderPayContext;
import com.example.demo.pojo.modle.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 支付宝支付
 *
 * @author dongxu
 * @create 2023-08-13 下午4:19
 */
@Component(value = PayForAliPayAdapter.PAY_KIND)
public class PayForAliPayAdapter implements PayAdapter {

    public static final String PAY_KIND = "ALI_PAY";

    @Override
    public boolean support(String adapter) {
        return SpringUtil.getBean(adapter) instanceof PayForAliPayAdapter;
    }

    @Override
    public String pay(String orderNumber, BigDecimal amount, String adapter) {
        // 数据模拟
        Order order = new Order();
        order.setOrderNumber(orderNumber)
                .setAmount(amount)
                .setPayAt(System.currentTimeMillis())
                .setUserName("dongxu")
                .setStatus(0);

        OrderPayContext.setOrder(order);
        // 模拟上下文取数据
        Order orderContext = OrderPayContext.getOrder();
        // remove()，防止内存泄漏
        OrderPayContext.removeAll();
        // 调用支付宝的Api
        return "订单号：" + orderNumber + "，支付金额：" + amount + "，支付方式：支付宝";
    }
}
