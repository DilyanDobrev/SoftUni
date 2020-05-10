package com.softuni.springautomappingex.services;

import com.softuni.springautomappingex.domain.dtos.UserLoginDto;
import com.softuni.springautomappingex.domain.dtos.UserRegisterDto;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logoutUser();

    boolean isAdmin();

    void getUserOwnedGames();
}
