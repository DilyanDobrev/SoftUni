package com.softuni.xmlcardealer.services.impl;


import com.softuni.xmlcardealer.models.dtos.seeddtos.CarSeedDto;
import com.softuni.xmlcardealer.models.dtos.CarPartsWrapperViewDto;
import com.softuni.xmlcardealer.models.dtos.CarPartsXmlViewDto;
import com.softuni.xmlcardealer.models.dtos.CarViewDto;
import com.softuni.xmlcardealer.models.dtos.PartViewDto;
import com.softuni.xmlcardealer.models.entities.Car;
import com.softuni.xmlcardealer.models.entities.Part;
import com.softuni.xmlcardealer.repositories.CarRepository;
import com.softuni.xmlcardealer.services.CarService;
import com.softuni.xmlcardealer.services.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ModelMapper modelMapper,
                          PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public void saveAll(List<CarSeedDto> carSeedDtos) {
        if (this.carRepository.count() != 0) {
            return;
        }

        Car[] cars = this.modelMapper.map(carSeedDtos, Car[].class);

        List<Part> allParts = this.partService.getAllParts();
        Random random = new Random();

        for (Car car : cars) {
            do {
                car.getParts().add(allParts.get(random.nextInt(allParts.size())));
                if (random.nextInt(5) == 0 && car.getParts().size() >= 10) {
                    break;
                }
            } while (car.getParts().size() <= 20);
        }

        this.carRepository.saveAll(Arrays.asList(cars));
    }

    @Override
    public List<CarViewDto> getCarsByMake(String make) {
        return this.carRepository
                .findByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> this.modelMapper.map(car, CarViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }


    @Override
    public CarPartsWrapperViewDto getAllCarsWithTheirParts() {
        CarPartsWrapperViewDto carsDto = new CarPartsWrapperViewDto();
        this.carRepository
                .findAll()
                .forEach(car -> {
                    CarPartsXmlViewDto carView = this.modelMapper.map(car, CarPartsXmlViewDto.class);
                    carView.setParts(car
                            .getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartViewDto.class))
                            .collect(Collectors.toSet())
                    );
                    carsDto.getCars().add(carView);
                });
        return carsDto;
    }
}
