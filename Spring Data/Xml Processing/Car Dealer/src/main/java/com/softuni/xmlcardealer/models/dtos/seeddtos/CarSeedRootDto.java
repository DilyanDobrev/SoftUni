package com.softuni.xmlcardealer.models.dtos.seeddtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {

    @XmlElement(name = "car")
    private List<CarSeedDto> cars;

    public CarSeedRootDto() {
    }

    public List<CarSeedDto> getCars() {
        return cars;
    }

    public void setCars(List<CarSeedDto> cars) {
        this.cars = cars;
    }
}
