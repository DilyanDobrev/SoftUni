package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDetailWrapperViewDto {

    @XmlElement(name = "sale")
    List<SaleDetailsViewDto> sales;

    public SaleDetailWrapperViewDto() {
    }

    public List<SaleDetailsViewDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDetailsViewDto> sales) {
        this.sales = sales;
    }
}
