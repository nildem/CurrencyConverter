package com.zooplus.challange.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zooplus.challange.domain.UserData;
import com.zooplus.challange.util.TextUtils;

public class SignupValidator implements Validator {
    public boolean supports(Class clazz) {
        return UserData.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        UserData data = (UserData) target;
        if (!TextUtils.getNotNull(data.getPassword()).equals(data.getPasswordrepeat())) {
            errors.rejectValue("passwordrepeat", "passwords.notmatch", null, "Password fields do not match");
        }
    }
}
