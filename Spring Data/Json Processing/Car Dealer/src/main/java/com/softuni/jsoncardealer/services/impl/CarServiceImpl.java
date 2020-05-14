package com.softuni.jsoncardealer.services.impl;

import com.softuni.jsoncardealer.models.dtos.CarPartsViewDto;
import com.softuni.jsoncardealer.models.dtos.CarViewDto;
import com.softuni.jsoncardealer.models.dtos.CarViewShortDto;
import com.softuni.jsoncardealer.models.dtos.PartViewDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.CarSeedDto;
import com.softuni.jsoncardealer.models.entities.Car;
import com.softuni.jsoncardealer.models.entities.Part;
import com.softuni.jsoncardealer.repositories.CarRepository;
import com.softuni.jsoncardealer.services.CarService;
import com.softuni.jsoncardealer.services.PartService;
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
    public void saveAll(CarSeedDto[] carSeedDtos) {
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
    public List<CarPartsViewDto> getAllCarsWithTheirParts() {
        return this.carRepository
                .findAll()
                .stream()
                .map(car -> {
                    CarPartsViewDto carView = new CarPartsViewDto();
                    carView.setCar(this.modelMapper.map(car, CarViewShortDto.class));
                    carView.setParts(car
                            .getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartViewDto.class))
                            .collect(Collectors.toSet())
                    );
                    return carView;
                })
                .collect(Collectors.toList());
    }
}
