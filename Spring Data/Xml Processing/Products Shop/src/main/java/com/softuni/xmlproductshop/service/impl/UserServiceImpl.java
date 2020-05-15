package com.softuni.xmlproductshop.service.impl;

import com.softuni.xmlproductshop.models.dtos.seeddtos.UserSeedDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.*;
import com.softuni.xmlproductshop.models.entities.User;
import com.softuni.xmlproductshop.repositories.UserRepository;
import com.softuni.xmlproductshop.service.UserService;
import com.softuni.xmlproductshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(List<UserSeedDto> userSeedDtos) {
        if (this.userRepository.count() != 0) {
            return;
        }

        userSeedDtos
                .forEach(userSeedDto -> {
                    if (this.validationUtil.isValid(userSeedDto)) {
                        User user = this.modelMapper.map(userSeedDto, User.class);
                        this.userRepository.saveAndFlush(user);
                    } else {
                        this.validationUtil.getViolations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long rndId = random.nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne(rndId);
    }

    @Override
    public List<UserSoldOneDto> getAllUsersSoldAtLeastOneProduct() {
        return this.userRepository.findAllUsersSoldAtLeastOneProduct()
                .stream()
                .map(u -> {
                    UserSoldOneDto userSoldOneDto = this.modelMapper.map(u, UserSoldOneDto.class);
                    userSoldOneDto.setSoldProducts(u.getSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .map(product -> this.modelMapper.map(product, ProductsSoldDto.class))
                            .collect(Collectors.toList()));
                    return userSoldOneDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserWrapperSellsDto getAllUserSells() {
        List<UserSellsDto> userSellsDtos = this.userRepository.findAllUsersWithSortedSoldProducts()
                .stream()
                .map(dto -> {
                    UserSellsDto userSellsDto = this.modelMapper.map(dto, UserSellsDto.class);

                    ProductsSoldCountDto productsSoldCountDto = new ProductsSoldCountDto();
                    productsSoldCountDto.setProducts(dto.getSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .map(product -> this.modelMapper.map(product, ProductNameAndPriceDto.class))
                            .collect(Collectors.toList()));

                    productsSoldCountDto.setCount(productsSoldCountDto.getProducts().size());

                    userSellsDto.setSoldProducts(productsSoldCountDto);

                    return userSellsDto;
                })
                .collect(Collectors.toList());

        UserWrapperSellsDto userWrapperSellsDto = new UserWrapperSellsDto();

        userWrapperSellsDto.setUserCount(userSellsDtos.size());
        userWrapperSellsDto.setUsers(userSellsDtos);

        return userWrapperSellsDto;
    }
}
