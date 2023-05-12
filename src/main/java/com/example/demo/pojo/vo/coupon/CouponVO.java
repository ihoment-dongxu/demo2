package com.example.demo.pojo.vo.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 优惠券
 *
 * @author dongxu
 */
@Data
public class CouponVO implements Serializable {

    private Integer id;

    @ApiModelProperty("名称")
    private String name;


    @ApiModelProperty("优惠券码")
    private String couponCode;

    @ApiModelProperty("使用类型：0->通用；1->指定分类；2->指定商品")
    private Integer useType;

    @ApiModelProperty("金额/百分比")
    private BigDecimal value;

    @ApiModelProperty("类型：0->金额, 1->百分比")
    private Integer valueType;

    @ApiModelProperty("货币")
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

    @ApiModelProperty("领取数量")
    private Integer receiveCount;

    @ApiModelProperty("已使用数量")
    private Integer useCount;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("创建时间,用于排序/展示")
    private Long createAt;

    @ApiModelProperty("是否删除： 1删除 0未删除")
    private Integer disable;

    private Timestamp createTime;

    private Timestamp updateTime;

}
