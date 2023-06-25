package com.example.demo.anno;

import com.example.demo.service.common.StringArrayValuable;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author dongxu
 * @create 2023-06-25 下午4:11
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = InEnumStringValueValidator.class
)
public @interface InEnumStringValue {
    /**
     * 实现了StringArrayValuable的类
     */
    Class<? extends StringArrayValuable> value();

    String message() default "必须在指定范围 {value}";

    /**
     * groups 属性用于指定在验证过程中要应用的验证组。验证组是一种逻辑分组，允许您根据不同的验证场景选择性地应用验证规则。通过在注解中指定 groups 属性，您可以在特定的验证场景下选择性地启用或禁用验证。
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * payload 属性用于指定自定义负载信息，可以用于在验证期间传递附加的元数据。负载信息可以是任何类型的对象，通常用于与验证器进行交互或携带额外的验证信息。
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
