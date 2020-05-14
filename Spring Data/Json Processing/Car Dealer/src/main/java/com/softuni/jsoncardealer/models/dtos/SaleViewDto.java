package com.softuni.jsoncardealer.models.dtos;

public class SaleViewDto {

    private Double discount;
    private CarViewDto car;

    public SaleViewDto() {
    }

    public SaleViewDto(Double discount, CarViewDto car) {
        this.discount = discount;
        this.car = car;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CarViewDto getCar() {
        return car;
    }

    public void setCar(CarViewDto car) {
        this.car = car;
    }
}
