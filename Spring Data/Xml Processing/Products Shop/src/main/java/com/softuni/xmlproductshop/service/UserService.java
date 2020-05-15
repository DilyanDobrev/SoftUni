package com.softuni.xmlproductshop.service;

import com.softuni.xmlproductshop.models.dtos.seeddtos.UserSeedDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.UserSoldOneDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.UserWrapperSellsDto;
import com.softuni.xmlproductshop.models.entities.User;

import java.util.List;

public interface UserService {

    void seedUsers(List<UserSeedDto> userSeedDtos);

    User getRandomUser();

    List<UserSoldOneDto> getAllUsersSoldAtLeastOneProduct();

    UserWrapperSellsDto getAllUserSells();
}
