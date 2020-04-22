package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.PASSENGERS_FILE_PATH;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final TownService townService;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGERS_FILE_PATH));
    }

    @Override
    public String importPassengers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PassengerSeedDto[] dtos = this.gson.fromJson(new FileReader(PASSENGERS_FILE_PATH), PassengerSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(passengerSeedDto -> {
                    Passenger passenger = this.modelMapper.map(passengerSeedDto, Passenger.class);
                    Town byName = this.townService.getByName(passengerSeedDto.getTown());

                    if (this.validationUtil.isValid(passengerSeedDto)
                            && this.passengerRepository.findByEmail(passengerSeedDto.getEmail()) == null
                            && byName != null) {

                        passenger.setTown(byName);

                        sb.append(String.format("Successfully imported Passenger %s - %s",
                                passengerSeedDto.getLastName(),
                                passengerSeedDto.getEmail()));

                        this.passengerRepository.saveAndFlush(passenger);
                    } else {
                        sb.append("Invalid Passenger");
                    }

                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();

        this.passengerRepository.findAllByTicketsCount()
                .forEach(p -> sb.append(String.format("Passenger %s %s\n" +
                                "\tEmail - %s\n" +
                                "\tPhone - %s\n" +
                                "\tNumber of tickets - %d\n",
                        p.getFirstName(), p.getLastName(),
                        p.getEmail(), p.getPhoneNumber(),
                        p.getTickets().size()))
                        .append(System.lineSeparator()));

        return sb.toString();
    }

    @Override
    public Passenger getByEmail(String email) {
        return this.passengerRepository.findByEmail(email);
    }
}
