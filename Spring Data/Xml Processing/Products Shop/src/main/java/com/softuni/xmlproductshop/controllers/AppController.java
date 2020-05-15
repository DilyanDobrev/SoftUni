package com.softuni.xmlproductshop.controllers;

import com.softuni.xmlproductshop.models.dtos.seeddtos.CategorySeedRootDto;
import com.softuni.xmlproductshop.models.dtos.seeddtos.ProductSeedRootDto;
import com.softuni.xmlproductshop.models.dtos.seeddtos.UserSeedRootDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.*;
import com.softuni.xmlproductshop.service.CategoryService;
import com.softuni.xmlproductshop.service.ProductService;
import com.softuni.xmlproductshop.service.UserService;
import com.softuni.xmlproductshop.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.softuni.xmlproductshop.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AppController(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
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

    private void writeUsersAndProducts() throws IOException, JAXBException {
        this.xmlParser.marshalToFile(QUERY_EX4_PATH, this.userService.getAllUserSells());
    }

    private void writeCategoriesByProductsCount() throws IOException, JAXBException {
        List<CategoryProductsDto> categoryProductsDtos = this.categoryService.getCategoriesByProductsCount();
        CategoryProductWrapperDto wrapper = new CategoryProductWrapperDto();
        wrapper.setCategories(categoryProductsDtos);

        this.xmlParser.marshalToFile(QUERY_EX3_PATH, wrapper);
    }

    private void writeSuccessfullySoldProducts() throws IOException, JAXBException {
        List<UserSoldOneDto> userSoldOneDtos = this.userService.getAllUsersSoldAtLeastOneProduct();
        UserSoldOneWrapperDto wrapper = new UserSoldOneWrapperDto();
        wrapper.setUsers(userSoldOneDtos);

        this.xmlParser.marshalToFile(QUERY_EX2_PATH, wrapper);
    }

    private void writeProductsInRange() throws IOException, JAXBException {
        List<ProductInRangeDto> allProductsInRange = this.productService.getAllProductsInRange();
        ProductsInRangeWrapperDto wrapper = new ProductsInRangeWrapperDto();
        wrapper.setProducts(allProductsInRange);

        this.xmlParser.marshalToFile(QUERY_EX1_PATH, wrapper);
    }

    private void seedProducts() throws FileNotFoundException, JAXBException {
        ProductSeedRootDto productSeedRootDto = this.xmlParser
                .unmarshalFromFile(PRODUCTS_FILE_PATH, ProductSeedRootDto.class);
        this.productService.seedProducts(productSeedRootDto.getProducts());
    }

    private void seedCategories() throws FileNotFoundException, JAXBException {
        CategorySeedRootDto categorySeedRootDto = this.xmlParser
                .unmarshalFromFile(CATEGORIES_FILE_PATH, CategorySeedRootDto.class);
        this.categoryService.seedCategories(categorySeedRootDto.getCategories());
    }

    private void seedUsers() throws FileNotFoundException, JAXBException {
        UserSeedRootDto userSeedRootDto = this.xmlParser
                .unmarshalFromFile(USERS_FILE_PATH, UserSeedRootDto.class);
        this.userService.seedUsers(userSeedRootDto.getUsers());
    }
}
