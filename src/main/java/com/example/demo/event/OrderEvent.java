package com.example.demo.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:15
 */
@Setter
@Getter
public class OrderEvent extends ApplicationEvent {

    private Integer orderStatus;
    private String orderNumber;

    public OrderEvent(Object source, Integer orderStatus, String orderNumber) {
        super(source);
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
    }
}
