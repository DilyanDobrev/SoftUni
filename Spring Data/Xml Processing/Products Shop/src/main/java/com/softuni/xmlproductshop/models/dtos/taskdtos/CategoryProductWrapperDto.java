package com.softuni.xmlproductshop.models.dtos.taskdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductWrapperDto {

    @XmlElement(name = "category")
    List<CategoryProductsDto> categories;

    public CategoryProductWrapperDto() {
    }

    public List<CategoryProductsDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductsDto> categories) {
        this.categories = categories;
    }
}
