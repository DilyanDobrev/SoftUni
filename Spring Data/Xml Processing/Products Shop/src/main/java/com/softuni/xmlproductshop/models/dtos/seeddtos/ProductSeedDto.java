package com.softuni.xmlproductshop.models.dtos.seeddtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {

    @XmlElement
    private String name;

    @XmlElement
    private BigDecimal price;

    public ProductSeedDto() {
    }

    @Length(min = 3, message = "Wrong product name length.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
