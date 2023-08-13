package com.example.demo.service.impl.order;

import com.example.demo.event.OrderEvent;
import com.example.demo.service.order.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:24
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void updateStatus(String orderNumber, Integer orderStatus) {
        System.out.println("订单事件发送......");
        OrderEvent orderEvent = new OrderEvent("", orderStatus, orderNumber);
        eventPublisher.publishEvent(orderEvent);
    }

    @EventListener
    public void onEvent(OrderEvent orderEvent) {
        System.out.println(orderEvent);
    }
}
