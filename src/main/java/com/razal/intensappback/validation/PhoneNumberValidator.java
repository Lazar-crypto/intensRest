package com.razal.intensappback.validation;

import com.razal.intensappback.validation.annotation.PhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint,String> {

    final String PATTERN = "\\+?\\d+";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(PATTERN, phone);
    }
}
