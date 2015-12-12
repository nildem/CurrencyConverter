package com.zooplus.challange.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zooplus.challange.domain.Country;
import com.zooplus.challange.domain.UserData;
import com.zooplus.challange.hibernate.DataManager;
import com.zooplus.challange.util.TextUtils;

@Controller
public class SignupController {
    @Autowired
    private SignupValidator signupValidator;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String doSignup(@ModelAttribute("user") @Valid UserData user, BindingResult result, ModelMap model) {
        signupValidator.validate(user, result);
        if (result.hasErrors()) {
            return returnError(user, result, model);
        }
        String errorCode = DataManager.getInstance().createUser(user);
        if (TextUtils.isNotEmptyTrimmedStr(errorCode)) {
            result.rejectValue("email", errorCode);
            returnError(user, result, model);
        }
        return "signedup";
    }

    private String returnError(UserData user, BindingResult result, ModelMap model) {
        model.addAttribute("user", user);
        model.put(BindingResult.class.getName() + ".user", result);
        return "signup";
    }

    @ModelAttribute("countries")
    public List<Country> initializeCountries() {
        return DataManager.getInstance().getCountries();
    }
}
