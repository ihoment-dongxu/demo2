package com.example.demo.service.factory;

import com.example.demo.pojo.constant.CouponTypeEnum;
import com.example.demo.pojo.dto.coupon.ReceiveCouponDTO;
import com.example.demo.service.strategy.coupon.receive.AbstractReceiveCouponStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 领取优惠券策略工厂
 *
 * @author dongxu
 * @create 2023-08-23 下午10:25
 */
public class ReceiveCouponStrategyFactory {
    /**
     * 策略Map
     */
    static Map<CouponTypeEnum, AbstractReceiveCouponStrategy<?>> receiveCouponStrategyMap = new HashMap<>();

    /**
     * 设置策略
     *
     * @param strategyName 策略名
     * @param strategy     策略
     * @param <K>
     */
    public static <K extends ReceiveCouponDTO> void register(CouponTypeEnum strategyName, AbstractReceiveCouponStrategy<K> strategy) {
        receiveCouponStrategyMap.put(strategyName, strategy);
    }

    /**
     * 获取策略
     * @param strategyName
     * @param <K>
     * @return
     */
    @SuppressWarnings("uncheck")
    public static <K extends ReceiveCouponDTO > AbstractReceiveCouponStrategy<K> getFactoryByName(CouponTypeEnum strategyName){
        return (AbstractReceiveCouponStrategy<K>) receiveCouponStrategyMap.get(strategyName);
    }

}
