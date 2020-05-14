package com.softuni.springjson.controllers;

import com.google.gson.Gson;
import com.softuni.springjson.constants.GlobalConstants;
import com.softuni.springjson.models.dtos.seeddtos.CategorySeedDto;
import com.softuni.springjson.models.dtos.taskdtos.CategoryProductsDto;
import com.softuni.springjson.models.dtos.taskdtos.ProductInRangeDto;
import com.softuni.springjson.models.dtos.seeddtos.ProductSeedDto;
import com.softuni.springjson.models.dtos.seeddtos.UserSeedDto;
import com.softuni.springjson.models.dtos.taskdtos.UserSoldOneDto;
import com.softuni.springjson.models.dtos.taskdtos.UserWrapperSellsDto;
import com.softuni.springjson.service.CategoryService;
import com.softuni.springjson.service.ProductService;
import com.softuni.springjson.service.UserService;
import com.softuni.springjson.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.softuni.springjson.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

        //Ex 1
        //this.writeProductsInRange();

        //Ex 2
        //this.writeSuccessfullySoldProducts();

        //Ex 3
        //this.writeCategoriesByProductsCount();

        //Ex 4
        //this.writeUsersAndProducts();
    }

    private void writeUsersAndProducts() throws IOException {
        UserWrapperSellsDto dtos = this.userService.getAllUserSells();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX4_PATH);
    }

    private void writeCategoriesByProductsCount() throws IOException {
        List<CategoryProductsDto> dtos = this.categoryService.getCategoriesByProductsCount();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX3_PATH);
    }

    private void writeSuccessfullySoldProducts() throws IOException {
        List<UserSoldOneDto> dtos = this.userService.getAllUsersSoldAtLeastOneProduct();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX2_PATH);
    }

    private void writeProductsInRange() throws IOException {
        List<ProductInRangeDto> dtos = this.productService.getAllProductsInRange();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, QUERY_EX1_PATH);
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.PRODUCTS_FILE_PATH),
                        ProductSeedDto[].class);

        this.productService.seedProducts(dtos);

    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(CATEGORIES_FILE_PATH),
                        CategorySeedDto[].class);

        this.categoryService.seedCategories(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos = this.gson
                .fromJson(new FileReader(USERS_FILE_PATH),
                        UserSeedDto[].class);

        this.userService.seedUsers(dtos);
    }
}
