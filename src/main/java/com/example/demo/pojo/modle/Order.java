package com.example.demo.pojo.modle;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 订单
 *
 * @author dongxu
 * @create 2023-08-20 上午10:02
 */
@Data
@Accessors(chain = true)
public class Order {
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 支付时间
     */
    private Long payAt;
    /**
     * 支付状态 0-支付失败，1-未支付，2支付成功
     */
    private Integer status;
}
