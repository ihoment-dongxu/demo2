package com.example.demo.event.listener;

import com.example.demo.event.OrderEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:17
 */
@Component
public class OrderListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println("订单号 ： " + event.getOrderNumber());
        System.out.println("当前状态：" + event.getOrderStatus());
    }
}
