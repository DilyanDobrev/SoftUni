package com.softuni.xmlcardealer.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartsViewDto {

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlTransient
    private CarViewShortDto car;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private Set<PartViewDto> parts;

    public CarPartsViewDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public CarViewShortDto getCar() {
        return car;
    }

    public void setCar(CarViewShortDto car) {
        this.car = car;
    }

    public Set<PartViewDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartViewDto> parts) {
        this.parts = parts;
    }
}
