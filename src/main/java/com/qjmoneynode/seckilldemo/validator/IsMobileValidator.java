package com.qjmoneynode.seckilldemo.validator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qjmoneynode.seckilldemo.utils.ValidatorUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author qjmoneynode
 * @version 1.0
 * @createTime 2023-07-11  16:38
 * @description 手机号码校验规则
 */

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
