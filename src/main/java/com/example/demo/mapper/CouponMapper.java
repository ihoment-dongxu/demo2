package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.modle.Coupon;

import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author dongxu
 */
public interface CouponMapper extends BaseMapper<Coupon> {


    default List<Coupon> listCoupon() {
        LambdaQueryWrapper<Coupon> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Coupon::getDisable, 0);
        return this.selectList(queryWrapper);
    }
}
