package com.softuni.xmlproductshop.models.dtos.seeddtos;

import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {

    @XmlElement
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
