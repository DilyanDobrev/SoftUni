package com.softuni.springautomappingex.services.impl;

import com.softuni.springautomappingex.domain.dtos.UserDto;
import com.softuni.springautomappingex.domain.dtos.UserLoginDto;
import com.softuni.springautomappingex.domain.dtos.UserRegisterDto;
import com.softuni.springautomappingex.domain.entities.Game;
import com.softuni.springautomappingex.domain.entities.Role;
import com.softuni.springautomappingex.domain.entities.User;
import com.softuni.springautomappingex.repositories.UserRepository;
import com.softuni.springautomappingex.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user == null) {
            System.out.println("Incorrect username / password.");
        } else {
            this.userDto = this.modelMapper
                    .map(user, UserDto.class);

            System.out.println("Successfully logged in " + user.getFullName());
        }
    }

    @Override
    public void logoutUser() {

        if (userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            String name = this.userDto.getFullName();
            this.userDto = null;
            System.out.printf("User %s successfully logged out%n", name);
        }
    }

    @Override
    public void getUserOwnedGames() {
        if (userDto == null) {
            System.out.println("There is no registered user.");
            return;
        }
        Set<Game> games =
                userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).getGames();
        games.forEach(g -> System.out.println(g.getTitle()));
    }

    @Override
    public boolean isAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }
}
