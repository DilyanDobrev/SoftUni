package com.softuni.jsoncardealer.models.dtos;

import java.util.Set;

public class CarPartsViewDto {

    private CarViewShortDto car;
    private Set<PartViewDto> parts;

    public CarPartsViewDto() {
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
