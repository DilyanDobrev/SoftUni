package com.softuni.springautomappingex.services;

import com.softuni.springautomappingex.domain.dtos.GameAddDto;
import com.softuni.springautomappingex.domain.dtos.GameEditDto;
import com.softuni.springautomappingex.domain.dtos.ViewAllGames;
import com.softuni.springautomappingex.domain.dtos.ViewDetailGame;

import java.util.List;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    void editGame(GameEditDto gameEditDto, String[] data);

    void deleteGame(Long id);

    List<ViewAllGames> getAllGames();

    ViewDetailGame getGameDetails(String title);
}
