package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchaseWrapperViewDto {

    @XmlElement(name = "customer")
    List<CustomerPurchasesViewDto> customers;

    public CustomerPurchaseWrapperViewDto() {
    }

    public List<CustomerPurchasesViewDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerPurchasesViewDto> customers) {
        this.customers = customers;
    }
}
