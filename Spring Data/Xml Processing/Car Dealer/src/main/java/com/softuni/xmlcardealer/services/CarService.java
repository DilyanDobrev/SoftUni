package com.softuni.xmlcardealer.services;


import com.softuni.xmlcardealer.models.dtos.CarPartsWrapperViewDto;
import com.softuni.xmlcardealer.models.dtos.CarViewDto;
import com.softuni.xmlcardealer.models.dtos.seeddtos.CarSeedDto;
import com.softuni.xmlcardealer.models.entities.Car;

import java.util.List;

public interface CarService {

    void saveAll(List<CarSeedDto> carSeedDtos);

    List<Car> getAllCars();

    List<CarViewDto> getCarsByMake(String toyota);

    CarPartsWrapperViewDto getAllCarsWithTheirParts();
}

