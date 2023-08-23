package com.example.demo.context;

import com.example.demo.pojo.modle.Order;

/**
 * 订单支付上下文
 * @author dongxu
 * @create 2023-08-20 上午10:01
 */
public class OrderPayContext {
    /**
     * 订单
     */
    private final static ThreadLocal<Order> order = new ThreadLocal<>();

    /**
     * 支付信息
     */
    private final static ThreadLocal<Integer> status = new ThreadLocal<>();

    public static void setOrder(Order order) {
        OrderPayContext.order.set(order);
    }

    public static Order getOrder() {
        return order.get();
    }

    public static void setStatus(Integer status) {
        OrderPayContext.status.set(status);
    }

    public static Integer getStatus() {
        return status.get();
    }

    /**
     * 使用后需要在程序中显式的remove，否则会一直存在在内存中，防止内存泄漏
     */
    public static void removeAll(){
        OrderPayContext.order.remove();
        OrderPayContext.status.remove();
    }

}
