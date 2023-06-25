package com.example.demo.pojo.constant;

import com.example.demo.service.common.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 公共状态枚举
 *
 * @author dongxu
 * @create 2023-06-25 下午4:48
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements IntArrayValuable {
    CLOSE(0, "禁用"),
    OPEN(1, "启用"),
    ;
    private int status;
    private String desc;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CommonStatusEnum::getStatus).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
