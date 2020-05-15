package com.softuni.xmlcardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthdayDto {

    @SerializedName("Id")
    @XmlElement
    private Long id;

    @SerializedName("Name")
    @XmlElement
    private String name;

    @SerializedName("BirthDate")
    @XmlElement(name = "birth-date")
    private Date birthDate;

    @SerializedName("IsYoungDriver")
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    @XmlTransient
    private Set<SaleViewDto> purchases;

    public CustomerByBirthdayDto() {
        this.purchases = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewDto> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<SaleViewDto> purchases) {
        this.purchases = purchases;
    }
}
