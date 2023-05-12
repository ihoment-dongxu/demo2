package com.example.demo.assembler.coupon;

import com.example.demo.pojo.modle.Coupon;
import com.example.demo.pojo.request.coupon.CouponSaveRequest;
import com.example.demo.pojo.vo.coupon.CouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-04-09 下午8:50
 */
@Mapper
public interface CouponConvert {

    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    Coupon convert(CouponSaveRequest bean);

    List<CouponVO> convertList(List<Coupon> coupons);
}
