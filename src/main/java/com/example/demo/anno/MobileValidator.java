package com.example.demo.anno;

import com.example.demo.pojo.constant.RegularConstant;
import com.github.pagehelper.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号验证器
 *
 * @author dongxu
 * @create 2023-06-25 下午6:06
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (StringUtil.isEmpty(value)) {
            return true;
        }
        // 校验手机
        return RegularConstant.isMobileNumber(value);
    }
}
