package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.CARS_FILE_PATH;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        CarSeedDto[] dtos = this.gson.fromJson(new FileReader(CARS_FILE_PATH), CarSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(carSeedDto -> {
                    if (this.validationUtil.isValid(carSeedDto)) {
                        if (this.carRepository.findByMakeAndModelAndKilometers(carSeedDto.getMake(),
                                carSeedDto.getModel(), carSeedDto.getKilometers()) == null) {

                            Car car = this.modelMapper.map(carSeedDto, Car.class);
                            LocalDate localDate = LocalDate.parse(carSeedDto.getRegisteredOn(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            car.setRegisteredOn(localDate);

                            sb.append(String.format("Successfully imported car - %s - %s",
                                    carSeedDto.getMake(), carSeedDto.getModel()));
                            this.carRepository.saveAndFlush(car);
                        } else {
                            sb.append("Invalid car");
                        }
                    } else {
                        sb.append("Invalid car");
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        List<Car> allByPicturesCount = this.carRepository.findAllByPicturesCount();

        allByPicturesCount.forEach(p -> {
            sb.append(String.format("Car make - %s, model - %s\n" +
                    "\tKilometers - %d\n" +
                    "\tRegistered on - %s\n" +
                    "\tNumber of pictures - %d\n",
                    p.getMake(), p.getModel(), p.getKilometers(),
                    p.getRegisteredOn(), p.getPictures().size()))
                    .append(System.lineSeparator());
        });
        return sb.toString();
    }

    @Override
    public Optional<Car> getById(Integer id) {
        return this.carRepository.findById(id);
    }
}
