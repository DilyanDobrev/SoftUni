package com.softuni.springjson.service;

import com.softuni.springjson.models.dtos.seeddtos.UserSeedDto;
import com.softuni.springjson.models.dtos.taskdtos.UserSoldOneDto;
import com.softuni.springjson.models.dtos.taskdtos.UserWrapperSellsDto;
import com.softuni.springjson.models.entities.User;

import java.util.List;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();

    List<UserSoldOneDto> getAllUsersSoldAtLeastOneProduct();

    UserWrapperSellsDto getAllUserSells();
}
