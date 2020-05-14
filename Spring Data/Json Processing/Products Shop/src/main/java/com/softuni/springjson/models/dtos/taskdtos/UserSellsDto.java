package com.softuni.springjson.models.dtos.taskdtos;

import com.google.gson.annotations.Expose;

public class UserSellsDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private ProductsSoldCountDto soldProducts;

    public UserSellsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductsSoldCountDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsSoldCountDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
