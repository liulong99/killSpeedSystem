package com.ccgydx.spring.boot.speed.kill.system.valicator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description :校验器的规则类
 * @Author 刘龙
 * @Date 2020/3/16 21:25
 * @Version 1.0
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required=false;
    /**
     * 校验器的初始化方法，默认不能为空
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        boolean required = constraintAnnotation.required();
    }

    /**
     * 校验规则
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //如果这个值是必须的
        if(required){
            return MobileCheck.isMobile(s);
        }else{
            if(StringUtils.isEmpty(s)){
                return true;
            }else{
                return MobileCheck.isMobile(s);
            }
        }
    }
}
