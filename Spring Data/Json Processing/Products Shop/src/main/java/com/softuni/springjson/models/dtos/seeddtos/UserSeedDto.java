package com.softuni.springjson.models.dtos.seeddtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class UserSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;

    public UserSeedDto() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3, message = "Wrong last name length.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
