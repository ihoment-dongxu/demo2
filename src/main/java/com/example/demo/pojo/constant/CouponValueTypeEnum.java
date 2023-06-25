package com.example.demo.pojo.constant;

import com.example.demo.service.common.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dongxu
 * @create 2023-06-25 下午5:00
 */
@AllArgsConstructor
@Getter
public enum CouponValueTypeEnum implements IntArrayValuable {
    FIXED_AMOUNT(0, "固定金额"),
    PERCENTAGE(1, "百分比");
    private int type;
    private String desc;

    public static int[] ARRAYS = Arrays.stream(CouponValueTypeEnum.values()).mapToInt(CouponValueTypeEnum::getType).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
