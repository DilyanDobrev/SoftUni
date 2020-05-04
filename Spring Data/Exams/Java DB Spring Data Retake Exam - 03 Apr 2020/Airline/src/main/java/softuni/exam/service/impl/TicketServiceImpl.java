package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TicketSeedDto;
import softuni.exam.models.dtos.TicketSeedRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.GlobalConstants.TICKETS_FILE_PATH;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final PassengerService passengerService;
    private final TownService townService;
    private final PlaneService planeService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, PassengerService passengerService, TownService townService, PlaneService planeService) {
        this.ticketRepository = ticketRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.passengerService = passengerService;
        this.townService = townService;
        this.planeService = planeService;
    }


    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TicketSeedRootDto ticketSeedRootDto = this.xmlParser
                .unmarshalFromFile(TICKETS_FILE_PATH, TicketSeedRootDto.class);

        for (TicketSeedDto ticketSeedDto : ticketSeedRootDto.getTickets()) {
            if (!this.validationUtil.isValid(ticketSeedDto) ||
                    this.ticketRepository.findBySerialNumber(ticketSeedDto.getSerialNumber()) != null) {

                sb.append("Invalid Ticket");
                sb.append(System.lineSeparator());
                continue;
            }

            Ticket ticket = this.modelMapper.map(ticketSeedDto, Ticket.class);

            Town fromTown = this.townService.getByName(
                    this.modelMapper.map(ticketSeedDto.getFromTown(), Town.class).getName());
            Town toTown = this.townService.getByName(
                    this.modelMapper.map(ticketSeedDto.getToTown(), Town.class).getName());
            Passenger passenger = this.passengerService.getByEmail(
                    this.modelMapper.map(ticketSeedDto.getPassenger(), Passenger.class).getEmail());
            Plane plane = this.planeService.getByRegisterNumber(
                    this.modelMapper.map(ticketSeedDto.getPlane(), Plane.class).getRegisterNumber());

            if (fromTown == null || toTown == null || passenger == null || plane == null) {
                sb.append("Invalid Ticket");
                sb.append(System.lineSeparator());
                continue;
            }

            ticket.setFromTown(fromTown);
            ticket.setToTown(toTown);
            ticket.setPassenger(passenger);
            ticket.setPlane(plane);
//                                LocalDateTime localDateTime = LocalDateTime
//                                        .parse(ticketSeedDto.getTakeoff(),
//                                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                                ticket.setTakeoff(localDateTime);

            sb.append(String.format("Successfully imported Ticket %s - %s",
                    ticketSeedDto.getFromTown().getName(),
                    ticketSeedDto.getToTown().getName()));

            this.ticketRepository.saveAndFlush(ticket);

            sb.append(System.lineSeparator());
        }


        return sb.toString();
    }
}
