package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.TOWNS_FILE_PATH;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TownSeedDto[] dtos = this.gson.fromJson(new FileReader(TOWNS_FILE_PATH), TownSeedDto[].class);

        for (TownSeedDto townSeedDto : dtos) {
            if (!this.validationUtil.isValid(townSeedDto) ||
                    this.townRepository.findByName(townSeedDto.getName()) != null) {

                sb.append("Invalid Town");
                sb.append(System.lineSeparator());
                continue;
            }

            Town town = this.modelMapper.map(townSeedDto, Town.class);

            sb.append(String.format("Successfully imported Town %s - %d",
                    townSeedDto.getName(), townSeedDto.getPopulation()));
            this.townRepository.saveAndFlush(town);


            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public Town getByName(String name) {
        return this.townRepository.findByName(name);
    }
}
