package com.example.demo.controller;

import com.example.demo.pojo.request.coupon.CouponSaveRequest;
import com.example.demo.pojo.result.ResultBean;
import com.example.demo.pojo.vo.coupon.CouponVO;
import com.example.demo.service.coupon.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 优惠券
 *
 * @author dongxu
 * @create 2023-05-08 下午9:31
 */
@RestController
@RequestMapping(value ="/dx/v1/coupon")
public class CouponController {

   @Resource
   private CouponService couponService;

   @PostMapping(value = "/save")
   public ResultBean<Boolean> insertCoupon(@RequestBody @Valid CouponSaveRequest request){
      Boolean insertFlag = couponService.insertCoupon(request);
      return ResultBean.ok(insertFlag);
   }

   @GetMapping(value = "/list")
   public ResultBean<List<CouponVO>> listCoupon(){
      List<CouponVO> couponList = couponService.listCoupon();
      return ResultBean.ok(couponList);
   }

   @GetMapping(value = "/selectByCursor")
   public ResultBean<List<CouponVO>> selectByCursor(){
      List<CouponVO> couponList = couponService.selectByCursor();
      return ResultBean.ok(couponList);
   }
}
