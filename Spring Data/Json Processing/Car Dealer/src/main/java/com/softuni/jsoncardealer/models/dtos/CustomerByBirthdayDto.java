package com.softuni.jsoncardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CustomerByBirthdayDto {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private LocalDateTime birthDate;

    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
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
