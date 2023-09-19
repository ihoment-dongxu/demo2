package com.example.demo.service.strategy.coupon.receive;

import com.example.demo.pojo.constant.CouponTypeEnum;
import com.example.demo.pojo.dto.coupon.ReceiveCouponDTO;

/**
 * 新人券策略
 *
 * @author dongxu
 * @create 2023-08-23 下午10:46
 */
public class ReceiveNewComerCouponStrategy extends AbstractReceiveCouponStrategy<ReceiveCouponDTO> {
    @Override
    protected CouponTypeEnum getStrategyName() {
        return CouponTypeEnum.NEW_COMER;
    }

    @Override
    protected boolean receive(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("新人券领取");
        return true;
    }

    @Override
    protected boolean checkReceiveLimit(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("新人券领取次数检查");
        return true;
    }

    @Override
    protected boolean checkCouponTemplate(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("新人券模版检查");
        return true;
    }
}
