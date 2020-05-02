package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.PlayerSeedDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.ValidatorUtil;


import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.GlobalConstants.PLAYERS_FILE_PATH;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final TeamService teamService;
    private final PictureService pictureService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil, TeamService teamService, PictureService pictureService) {
        this.playerRepository = playerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.teamService = teamService;
        this.pictureService = pictureService;
    }

    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerSeedDto[] playerSeedDtos = this.gson.fromJson(new FileReader(PLAYERS_FILE_PATH), PlayerSeedDto[].class);
        for (PlayerSeedDto playerSeedDto : playerSeedDtos) {
            if (this.validatorUtil.isValid(playerSeedDto)) {
                if (this.playerRepository.findByFirstNameAndLastNameAndNumber(playerSeedDto.getFirstName(),
                        playerSeedDto.getLastName(), playerSeedDto.getNumber()) == null) {
                    Player player = this.modelMapper.map(playerSeedDto, Player.class);
                    Team team = this.teamService.getByName(playerSeedDto.getTeam().getName());
                    Picture picture = this.pictureService.getPictureByUrl(playerSeedDto.getPicture().getUrl());

                    if (team != null && picture != null) {
                        player.setTeam(team);
                        player.setPicture(picture);

                        this.playerRepository.saveAndFlush(player);
                    } else {
                        sb.append("Invalid player")
                                .append(System.lineSeparator());
                    }
                } else {
                    sb.append("Already in DB")
                            .append(System.lineSeparator());
                }
            } else {
                sb.append("Invalid player")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();
        this.playerRepository.findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal.valueOf(100000))
                .forEach(player ->sb.append(String.format("Player name: %s %s\n" +
                        "\tNumber: %d\n" +
                        "\tSalary: %.2f\n" +
                        "\tTeam: %s",
                        player.getFirstName(),
                        player.getLastName(),
                        player.getNumber(),
                        player.getSalary(),
                        player.getTeam().getName()))
                        .append(System.lineSeparator()));
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team: North Hub").append(System.lineSeparator());
        this.playerRepository.findAllByTeamName("North Hub")
                .forEach(player ->  sb.append(String.format("\tPlayer name: %s %s - %s\n\tNumber: %d\n",
                        player.getFirstName(),
                        player.getLastName(),
                        player.getPosition(),
                        player.getNumber())));
        return sb.toString();
    }


}
