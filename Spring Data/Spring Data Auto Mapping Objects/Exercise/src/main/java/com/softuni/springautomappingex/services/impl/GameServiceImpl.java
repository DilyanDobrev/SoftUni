package com.softuni.springautomappingex.services.impl;

import com.softuni.springautomappingex.domain.dtos.GameAddDto;
import com.softuni.springautomappingex.domain.dtos.GameEditDto;
import com.softuni.springautomappingex.domain.dtos.ViewAllGames;
import com.softuni.springautomappingex.domain.dtos.ViewDetailGame;
import com.softuni.springautomappingex.domain.entities.Game;
import com.softuni.springautomappingex.repositories.GameRepository;
import com.softuni.springautomappingex.services.GameService;
import com.softuni.springautomappingex.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (this.userService.isAdmin()) {
            Game game = this.modelMapper.map(gameAddDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            System.out.println("Added " + gameAddDto.getTitle());
        } else {
            System.out.println("Logged user must be admin.");
        }
    }


    @Override
    public void editGame(GameEditDto gameEditDto, String[] data) {
        Long id = Long.parseLong(data[1]);
        Game game = this.gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("There is no such game.");
            return;
        }

        for (int i = 2; i < data.length; i++) {
            String[] values = data[i].split("=");
            switch (values[0]) {
                case "title":
                    game.setTitle(values[1]);
                    break;
                case "price":
                    game.setPrice(new BigDecimal(values[1]));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(values[1]));
                    break;
                case "trailer":
                    game.setTrailer(values[1]);
                    break;
                case "image":
                    game.setImage(values[1]);
                    break;
                case "releaseDate":
                    LocalDate releaseDate =
                            LocalDate.parse(values[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    game.setReleaseDate(releaseDate);
                    break;
            }
        }
        if (this.userService.isAdmin()) {
            this.gameRepository.saveAndFlush(game);
            System.out.println("Edited " + game.getTitle());
        } else {
            System.out.println("Logged user must be admin.");
        }
    }

    @Override
    @Transactional
    public void deleteGame(Long id) {
        Game game = this.gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("There is no such game.");
            return;
        }

        if (this.userService.isAdmin()) {
            this.gameRepository.delete(game);
            this.gameRepository.flush();
            System.out.println("Deleted " + game.getTitle());
        } else {
            System.out.println("Logged user must be admin.");
        }
    }

    @Override
    public List<ViewAllGames> getAllGames() {
        List<Game> games = this.gameRepository.findAll();

       return games
               .stream()
               .map(g -> this.modelMapper.map(g, ViewAllGames.class))
               .collect(Collectors.toList());
    }

    @Override
    public ViewDetailGame getGameDetails(String title) {
        Game game = this.gameRepository.findByTitle(title);
        if (game == null) { return null; }

        return this.modelMapper.map(game, ViewDetailGame.class);
    }
}
