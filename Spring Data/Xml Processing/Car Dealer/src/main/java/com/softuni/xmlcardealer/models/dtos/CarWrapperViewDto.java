package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWrapperViewDto {

    @XmlElement(name = "car")
    List<CarViewDto> cars;

    public CarWrapperViewDto() {
    }

    public List<CarViewDto> getCars() {
        return cars;
    }

    public void setCars(List<CarViewDto> cars) {
        this.cars = cars;
    }
}
