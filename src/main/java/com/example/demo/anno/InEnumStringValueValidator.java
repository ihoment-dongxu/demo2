package com.example.demo.anno;

import com.example.demo.service.common.StringArrayValuable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * InEnumStringValue指定的验证器
 *
 * @author dongxu
 * @create 2023-06-25 下午4:17
 */
public class InEnumStringValueValidator implements ConstraintValidator<InEnumStringValue, String> {

    private List<String> values;

    @Override
    public void initialize(InEnumStringValue annotation) {
        StringArrayValuable[] values = annotation.value().getEnumConstants();
        if (values.length == 0) {
            this.values = Collections.emptyList();
        } else {
            this.values = Arrays.stream(values[0].strArray()).collect(Collectors.toList());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (values.contains(value)) {
            return true;
        }
        // 校验不通过，自定义提示语句 （因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                context.getDefaultConstraintMessageTemplate()
                        .replaceAll("\\{value}", values.toString())
        ).addConstraintViolation(); // 重新添加错误提示语句

        return false;
    }

}
