package com.example.demo.service.strategy.coupon.receive;

import com.example.demo.pojo.constant.CouponTypeEnum;
import com.example.demo.pojo.dto.coupon.ReceiveCouponDTO;
import com.example.demo.service.factory.ReceiveCouponStrategyFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * 领取优惠券
 *
 * @author dongxu
 * @create 2023-08-23 下午10:31
 */
@Slf4j
public abstract class AbstractReceiveCouponStrategy<K extends ReceiveCouponDTO> {

    @PostConstruct
    public void registerMyselfToFactory() {
        ReceiveCouponStrategyFactory.register(getStrategyName(), this);
    }

    /**
     * 获取策略名
     *
     * @return
     */
    protected abstract CouponTypeEnum getStrategyName();

    /**
     * 领取
     *
     * @param k 领取dto
     * @return true 成功
     */
    public boolean receiveCoupon(K k) {
        check(k);
        receive(k);
        return true;
    }

    /**
     * 领取优惠券
     *
     * @param k 领取dto
     * @return true 成功
     */
    protected abstract boolean receive(K k);

    /**
     * 优惠券检查
     *
     * @param k
     * @return
     */
    protected boolean check(K k) {
        // 券模版检查
        boolean t1 = checkCouponTemplate(k);
        // 领取限制检查
        boolean t2 = checkReceiveLimit(k);
        return t1 && t2;
    }

    /**
     * 领取限制检查
     *
     * @param k
     * @return
     */
    protected abstract boolean checkReceiveLimit(K k);

    /**
     * 券模版检查
     *
     * @param k
     * @return
     */
    protected abstract boolean checkCouponTemplate(K k);

}
