package com.softuni.jsoncardealer.services;

import com.softuni.jsoncardealer.models.dtos.CarPartsViewDto;
import com.softuni.jsoncardealer.models.dtos.CarViewDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.CarSeedDto;
import com.softuni.jsoncardealer.models.entities.Car;

import java.util.List;

public interface CarService {

    void saveAll(CarSeedDto[] carSeedDtos);

    List<Car> getAllCars();

    List<CarViewDto> getCarsByMake(String toyota);

    List<CarPartsViewDto> getAllCarsWithTheirParts();
}

