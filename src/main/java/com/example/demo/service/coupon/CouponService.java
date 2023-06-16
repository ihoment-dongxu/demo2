package com.example.demo.service.coupon;

import com.example.demo.pojo.request.coupon.CouponSaveRequest;
import com.example.demo.pojo.vo.coupon.CouponVO;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-05-08 下午9:37
 */
public interface CouponService {
    /**
     * 新增优惠券
     *
     * @param request 新增请求
     * @return boolean
     */
    Boolean insertCoupon(CouponSaveRequest request);

    List<CouponVO> listCoupon();

    /**
     * 流式查询
     *
     * @return
     */
    List<CouponVO> selectByCursor();
}
