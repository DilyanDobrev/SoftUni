package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierWrapperViewDto {

    @XmlElement(name = "supplier")
    List<SupplierViewDto> suppliers;

    public SupplierWrapperViewDto() {
    }

    public List<SupplierViewDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierViewDto> suppliers) {
        this.suppliers = suppliers;
    }
}
