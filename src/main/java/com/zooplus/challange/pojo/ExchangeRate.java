package com.zooplus.challange.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "exchangeRate", catalog = "zooplus")
public class ExchangeRate implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7616526240591280028L;
    private BigDecimal rate;
    private String currencyFrom;
    private String currencyTo;
    private Date convertionDate;
    private Integer id;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "currencyFrom", length = 3)
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    @NotNull
    @Column(name = "currencyTo", length = 3)
    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    @NotNull
    @Column(name = "rate")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @NotNull
    @Column(name = "convertionDate")
    public Date getConvertionDate() {
        return convertionDate;
    }

    public void setConvertionDate(Date convertionDate) {
        this.convertionDate = convertionDate;
    }
}