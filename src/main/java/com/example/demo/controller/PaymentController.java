package com.example.demo.controller;

import com.example.demo.pojo.result.ResultBean;
import com.example.demo.service.pay.PayForMuitServiceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 支付
 *
 * @author dongxu
 * @create 2023-08-13 下午3:59
 */
@RestController
@RequestMapping(value = "/dx/v1/payment")
public class PaymentController {

    @Resource
    private PayForMuitServiceAdapter payForMuitServiceAdapter;

    @GetMapping(value = "/pay")
    public ResultBean<String> pay(@RequestParam("orderNumber") String orderNumber,
                                  @RequestParam("amount") BigDecimal amount,
                                  @RequestParam("adapter") String adapter) {
        String payForAdapter = payForMuitServiceAdapter.payForAdapter(orderNumber, amount, adapter);
        return ResultBean.ok(payForAdapter);
    }

}
