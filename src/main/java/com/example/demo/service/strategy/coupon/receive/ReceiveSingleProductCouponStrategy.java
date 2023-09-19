package com.example.demo.service.strategy.coupon.receive;

import com.example.demo.pojo.constant.CouponTypeEnum;
import com.example.demo.pojo.dto.coupon.ReceiveCouponDTO;

/**
 * 单品券策略
 *
 * @author dongxu
 * @create 2023-08-23 下午10:46
 */
public class ReceiveSingleProductCouponStrategy extends AbstractReceiveCouponStrategy<ReceiveCouponDTO> {

    @Override
    protected CouponTypeEnum getStrategyName() {
        return CouponTypeEnum.SINGLE_PRODUCT;
    }

    @Override
    protected boolean receive(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("单品券领取");
        return true;
    }

    @Override
    protected boolean checkReceiveLimit(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("单品券领取次数检查");
        return true;
    }

    @Override
    protected boolean checkCouponTemplate(ReceiveCouponDTO receiveCouponDTO) {
        System.out.println("单品券模版检查");
        return true;
    }
}
