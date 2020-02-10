package com.yolyn.authpay.utils;

import com.yolyn.authpay.common.CommonException;
import com.yolyn.authpay.enums.CommonRespEnum;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @author houyl
 * @date 2020/2/9 20:32
 * @description
 */
public class ValidateUtils {
    public static void validateBean(Object o) {
        //failFast true检查完一个有错误就返回，false全部检查完把错误消息一起返回   默认false
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure().failFast(true);
        ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
        Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
        if (iterator.hasNext()) {
            throw new CommonException(CommonRespEnum.COMMON_MISS_PARAMS.getCode(), iterator.next()
                    .getMessage());
        }
    }
}
