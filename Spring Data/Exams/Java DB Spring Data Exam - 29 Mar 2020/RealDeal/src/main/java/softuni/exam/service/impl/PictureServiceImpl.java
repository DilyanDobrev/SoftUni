package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PictureSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static softuni.exam.constants.GlobalConstants.PICTURES_FILE_PATH;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CarService carService;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, CarService carService) {
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureSeedDto[] dtos = this.gson.fromJson(new FileReader(PICTURES_FILE_PATH), PictureSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(pictureSeedDto -> {
                    if (this.validationUtil.isValid(pictureSeedDto)) {
                        if (this.pictureRepository.findByName(pictureSeedDto.getName()) == null) {
                            Picture picture = this.modelMapper.map(pictureSeedDto, Picture.class);
                            LocalDateTime localDateTime = LocalDateTime.parse(pictureSeedDto.getDateAndTime(),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            Car car = this.carService.getById(pictureSeedDto.getCar()).orElse(null);
                            if (car != null) {
                                picture.setDateAndTime(localDateTime);
                                picture.setCar(car);

                                sb.append(String.format("Successfully import picture - %s",
                                        pictureSeedDto.getName()));
                                this.pictureRepository.saveAndFlush(picture);
                            } else {
                                sb.append("Invalid picture");
                            }
                        } else {
                            sb.append("Invalid picture");
                        }
                    } else {
                        sb.append("Invalid picture");
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public Set<Picture> getAllByCar(Car car) {
        return this.pictureRepository.findAllByCar(car);
    }
}
