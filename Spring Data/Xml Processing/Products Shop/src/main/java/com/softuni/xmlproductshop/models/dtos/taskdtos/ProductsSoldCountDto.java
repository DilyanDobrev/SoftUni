package com.softuni.xmlproductshop.models.dtos.taskdtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldCountDto {

    @XmlAttribute
    private Integer count;

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
