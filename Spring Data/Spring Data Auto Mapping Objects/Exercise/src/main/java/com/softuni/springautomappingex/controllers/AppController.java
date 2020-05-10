package com.softuni.springautomappingex.controllers;

import com.softuni.springautomappingex.domain.dtos.*;
import com.softuni.springautomappingex.services.GameService;
import com.softuni.springautomappingex.services.UserService;
import com.softuni.springautomappingex.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader bufferedReader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter command.");

            String[] data = this.bufferedReader.readLine().split("\\|");

            switch (data[0]) {
                case "RegisterUser":
                    registerUser(data);
                    break;
                case "LoginUser":
                    loginUser(data);
                    break;
                case "Logout":
                    this.userService.logoutUser();
                    break;
                case "AddGame":
                    try {
                        addGame(data);
                    } catch (NullPointerException e) {
                        System.out.println("Must be logged in as admin to add games");
                    }

                    break;
                case "EditGame":
                    try {
                        editGameEx(data);
                    } catch (NullPointerException e) {
                        System.out.println("Must be logged in as admin to add games");
                    }
                    break;
                case "DeleteGame":
                    try {
                    Long id = Long.parseLong(data[1]);
                    this.gameService.deleteGame(id);
                    } catch (NullPointerException e) {
                        System.out.println("Must be logged in as admin to add games");
                    }
                    break;
                case "AllGames":
                    printAllGames();
                    break;
                case "DetailGame":
                    printDetailGame(data[1]);
                break;
                case "OwnedGames":
                    this.userService.getUserOwnedGames();
                    break;
            }
        }
    }

    private void printDetailGame(String datum) {
        ViewDetailGame viewDetailGame =
                this.gameService.getGameDetails(datum);

        if (viewDetailGame == null) {
            System.out.println("Game with that name does not exist.");
            return;
        }

        System.out.printf("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s%n",
                viewDetailGame.getTitle(),
                viewDetailGame.getPrice(),
                viewDetailGame.getDescription(),
                viewDetailGame.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    private void printAllGames() {
        if (this.gameService.getAllGames().size() > 0) {
            this.gameService.getAllGames()
                    .forEach(g -> System.out.printf("%s %.2f%n", g.getTitle(), g.getPrice()));
        } else {
            System.out.println("There are no games.");
        }
    }

    private void editGameEx(String[] data) {

        GameEditDto gameEditDto = new GameEditDto();

        if (this.validationUtil.isValid(gameEditDto)) {
            this.gameService.editGame(gameEditDto, data);
        } else{
            this.validationUtil.getViolations(gameEditDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }

    private void addGame(String[] data) {
        GameAddDto gameAddDto = new GameAddDto(
                data[1], new BigDecimal(data[2]),
                Double.parseDouble(data[3]), data[4], data[5], data[6],
                LocalDate.parse(data[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        );

        if (this.validationUtil.isValid(gameAddDto)) {
            this.gameService.addGame(gameAddDto);
        } else {
            this.validationUtil.getViolations(gameAddDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }

    private void loginUser(String[] data) {
        UserLoginDto userLoginDto =
                new UserLoginDto(data[1], data[2]);
        this.userService.loginUser(userLoginDto);
    }

    private void registerUser(String[] data) {
        if (!data[2].equals(data[3])) {
            System.out.println("Passwords don't match.");
            return;
        }

        UserRegisterDto userRegisterDto =
                new UserRegisterDto(data[1], data[2], data[4]);

        if (this.validationUtil.isValid(userRegisterDto)) {
            this.userService.registerUser(userRegisterDto);

            System.out.println(userRegisterDto.getFullName() + " was registered.");
        } else {
            this.validationUtil.getViolations(userRegisterDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }
}
