package com.softuni.xmlproductshop.models.dtos.taskdtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldOneDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;


    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    List<ProductsSoldDto> soldProducts;

    public UserSoldOneDto() {
        this.soldProducts = new ArrayList<>();
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

    public List<ProductsSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
