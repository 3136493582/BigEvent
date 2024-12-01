package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.State;

/**
 * 自定义检验数据的类
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param s 传入的值
     * @param constraintValidatorContext
     * @return true通过检验，false检验失败
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.equals("已发布")||s.equals("草稿"))
            return true;
        else return false;
    }
}
