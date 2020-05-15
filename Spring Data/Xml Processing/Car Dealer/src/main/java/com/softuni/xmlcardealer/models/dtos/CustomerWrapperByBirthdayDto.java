package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWrapperByBirthdayDto {

    @XmlElement(name = "customer")
    List<CustomerByBirthdayDto> customers;

    public CustomerWrapperByBirthdayDto() {
    }

    public List<CustomerByBirthdayDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerByBirthdayDto> customers) {
        this.customers = customers;
    }
}
