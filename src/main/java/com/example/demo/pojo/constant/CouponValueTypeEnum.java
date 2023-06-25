package com.example.demo.pojo.constant;

import com.example.demo.service.common.IntArrayValuable;
import com.example.demo.service.common.StringArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dongxu
 * @create 2023-06-25 下午5:00
 */
@AllArgsConstructor
@Getter
public enum CouponValueTypeEnum implements IntArrayValuable, StringArrayValuable {
    FIXED_AMOUNT(0, "固定金额"),
    PERCENTAGE(1, "百分比");
    private int type;
    private String desc;

    public static int[] ARRAYS = Arrays.stream(CouponValueTypeEnum.values()).mapToInt(CouponValueTypeEnum::getType).toArray();

    public static String[] DESC_ARRAYS = Arrays.stream(CouponValueTypeEnum.values()).map(CouponValueTypeEnum::getDesc).toArray(String[]::new);

    @Override
    public int[] intArray() {
        return ARRAYS;
    }


    @Override
    public String[] strArray() {
        return DESC_ARRAYS;
    }
}
