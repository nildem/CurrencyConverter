package com.zooplus.challange.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.zooplus.challange.util.TextUtils;

public class UserData {
    @Size(min = 1, max = 64)
    private String name;
    @Size(min = 1, max = 64)
    private String surname;
    @Size(min = 8, max = 64)
    private String password;
    @Size(min = 8, max = 64)
    private String passwordrepeat;
    @Size(min = 3, max = 64)
    @Pattern(regexp = TextUtils.EMAIL_PATTERN)
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private String birthday;
    @Size(min = 1, max = 64)
    private String addressLine1;
    private String addressLine2;
    @Size(min = 1, max = 64)
    private String city;
    @Size(min = 1, max = 64)
    private String postalCode;
    @Size(min = 1, max = 64)
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordrepeat() {
        return passwordrepeat;
    }

    public void setPasswordrepeat(String passwordrepeat) {
        this.passwordrepeat = passwordrepeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
