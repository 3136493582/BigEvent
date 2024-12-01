package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;

import java.lang.annotation.*;

/**
 * 自定义注解校验发布状态
 */
@Documented//元注解
@Constraint(
        validatedBy = StateValidation.class
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //失败提示信息
    String message() default "{发布状态只能是已发布或者草稿}";

    //分组信息
    Class<?>[] groups() default {};

    //重载附加信息
    Class<? extends Payload>[] payload() default {};
}
