package com.zooplus.challange.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", catalog = "zooplus")
public class User implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4484063358596411980L;
    private Integer id;
    private String name;
    private String surname;
    private String passHash;
    private String email;
    private Date birthday;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private String country;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //name
    @Column(name = "name", length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //surname
    @Column(name = "surname", length = 64)
    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //
    @Column(name = "passHash", length = 64)
    public String getPassHash() {
        return this.passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    //
    @Column(name = "email", length = 64)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //
    @Column(name = "addressLine1", length = 64)
    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    //
    @Column(name = "addressLine2", length = 64)
    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    //
    @Column(name = "city", length = 64)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //
    @Column(name = "country", length = 3)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //
    @Column(name = "postalCode", length = 16)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    //
    @Column(name = "birthday")
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthDay) {
        this.birthday = birthDay;
    }
}
