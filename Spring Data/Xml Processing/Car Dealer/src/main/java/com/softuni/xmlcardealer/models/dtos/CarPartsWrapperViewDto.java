package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartsWrapperViewDto {

    @XmlElement(name = "car")
    List<CarPartsXmlViewDto> cars;

    public CarPartsWrapperViewDto() {
        this.cars = new ArrayList<>();
    }

    public List<CarPartsXmlViewDto> getCars() {
        return cars;
    }

    public void setCars(List<CarPartsXmlViewDto> cars) {
        this.cars = cars;
    }
}
