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
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dongxu
 * @create 2023-05-08 下午9:51
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper couponMapper;

    @Resource(name = "couponThreadPoolExecutor")
    private ThreadPoolExecutor couponThreadPoolExecutor;

    @Override
    public Boolean insertCoupon(CouponSaveRequest request) {
        couponThreadPoolExecutor.execute(() -> {
            request.setCouponCode(CouponCodeUtils.generateCouponCode());
            Coupon coupon = CouponConvert.INSTANCE.convert(request);
            couponMapper.insert(coupon);
        });
        return Boolean.TRUE;
    }

    @Override
    public List<CouponVO> listCoupon() {
        List<Coupon> coupons = couponMapper.listCoupon();
        return CouponConvert.INSTANCE.convertList(coupons);
    }

}
