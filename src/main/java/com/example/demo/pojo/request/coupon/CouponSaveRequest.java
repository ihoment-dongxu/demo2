package com.example.demo.pojo.request.coupon;

import com.example.demo.anno.InEnumIntValue;
import com.example.demo.pojo.constant.CouponValueTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增优惠券请求
 *
 * @author dongxu
 * @create 2023-05-08 下午9:41
 */
@Data
public class CouponSaveRequest {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("优惠券码")
    private String couponCode;

    @ApiModelProperty("使用类型：0->通用；1->指定分类；2->指定商品")
    private Integer useType;

    @ApiModelProperty("金额/百分比")
    private BigDecimal value;

    @ApiModelProperty("类型：0->金额, 1->百分比")
    @InEnumIntValue(value = CouponValueTypeEnum.class, message = "必须在指定范围内 {value}")
    private Integer valueType;

    @ApiModelProperty("货币")
//    @InEnumStringValue(value = CouponValueTypeEnum.class,message = "必须在指定范围内 {value}")
    private String currency;

    @ApiModelProperty("每人限领张数")
    private Integer perLimit;

    @ApiModelProperty("使用门槛；0表示无门槛")
    private BigDecimal minPoint;

    @ApiModelProperty("开始使用时间")
    private Long startTime;

    @ApiModelProperty("结束使用时间")
    private Long endTime;

    @ApiModelProperty("数量限制")
    private Integer usageLimit;

}
