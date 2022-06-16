package com.utils;

import com.enums.ErrorEnum;
import com.exception.BizException;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 2:09 下午
 * description：校验辅助类
 */
public class ValidatorHelper {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure()
            .buildValidatorFactory().getValidator();

    private static final Logger log = LogFactory.COMMON_LOG;

    public static <T> void validate(T param) {
        if (param instanceof Collection) {
            ((Collection<?>) param).forEach(ValidatorHelper::validateSingle);
        } else {
            validateSingle(param);
        }
    }

    /**
     * 校验
     * @param param
     * @return
     *
     */
    public static <T> void validateSingle(T param) {
        StringBuilder message = new StringBuilder();
        if (param != null) {
            try {
                Set<ConstraintViolation<T>> violations = validator.validate(param);
                if (violations == null || violations.isEmpty()) {
                    return;
                }
                for (ConstraintViolation c : violations) {
                    message.append(c.getMessage()).append(",");
                }
            } catch (Exception e) {
                log.error("[ValidatorHelper#validateSingle] error, e={}", e);
                throw new BizException(ErrorEnum.INNER_ERROR_WITH_MSG.getCode(), String.format(ErrorEnum.INNER_ERROR_WITH_MSG.getMsg(), e.getMessage()));
            }
        }
        throw new BizException(ErrorEnum.PARAM_ERROR_WITH_MSG.getCode(), String.format(ErrorEnum.PARAM_ERROR_WITH_MSG.getMsg(), message.toString()));
    }

    private ValidatorHelper() {

    }
}
