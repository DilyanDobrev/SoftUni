package com.softuni.springjson.models.dtos.seeddtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {

    @Expose
    private String name;

    public CategorySeedDto() {
    }

    @Length(min = 3, max = 15, message = "Wrong category name length.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
