package com.softuni.springjson.models.dtos.taskdtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ProductsSoldCountDto {
    @Expose
    private Integer count;
    @Expose
    private List<ProductNameAndPriceDto> products;

    public ProductsSoldCountDto() {
        this.products = new ArrayList<>();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductNameAndPriceDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNameAndPriceDto> products) {
        this.products = products;
    }
}
