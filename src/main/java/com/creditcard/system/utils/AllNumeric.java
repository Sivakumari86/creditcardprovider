package com.creditcard.system.utils;

import static com.creditcard.system.config.ServerConstants.NOT_NUMERIC;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AllNumericValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllNumeric {
    String message() default NOT_NUMERIC;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}