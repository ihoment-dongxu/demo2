package com.example.demo.controller;

import com.example.demo.pojo.result.ResultBean;
import com.example.demo.service.order.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dongxu
 * @create 2023-04-15 下午2:21
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/updateStatus")
    public ResultBean updateStatus(String orderNumber,Integer orderStatus){
        orderService.updateStatus(orderNumber,orderStatus);
        return ResultBean.success();
    }
}
