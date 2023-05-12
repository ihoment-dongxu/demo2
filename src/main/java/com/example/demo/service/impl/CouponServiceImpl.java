package com.example.demo.service.impl;

import com.example.demo.assembler.coupon.CouponConvert;
import com.example.demo.mapper.CouponMapper;
import com.example.demo.pojo.modle.Coupon;
import com.example.demo.pojo.request.coupon.CouponSaveRequest;
import com.example.demo.pojo.vo.coupon.CouponVO;
import com.example.demo.service.coupon.CouponService;
import com.example.demo.utils.CouponCodeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dongxu
 * @create 2023-05-08 下午9:51
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper couponMapper;

    @Override
    public Boolean insertCoupon(CouponSaveRequest request) {
        request.setCouponCode(CouponCodeUtils.generateCouponCode());
        Coupon coupon = CouponConvert.INSTANCE.convert(request);
        int rows = couponMapper.insert(coupon);
        return rows > 0;
    }

    @Override
    public List<CouponVO> listCoupon() {
        List<Coupon> coupons = couponMapper.listCoupon();
        return CouponConvert.INSTANCE.convertList(coupons);
    }

}
