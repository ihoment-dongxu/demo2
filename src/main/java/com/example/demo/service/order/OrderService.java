package com.example.demo.service.order;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:24
 */
public interface OrderService {

    /**
     * 修改订单状态
     *
     * @param orderNumber 订单号
     * @param orderStatus 订单状态
     */
    void updateStatus(String orderNumber, Integer orderStatus);

}
