package com.softuni.springjson.service.impl;

import com.softuni.springjson.models.dtos.taskdtos.ProductInRangeDto;
import com.softuni.springjson.models.dtos.seeddtos.ProductSeedDto;
import com.softuni.springjson.models.entities.Product;
import com.softuni.springjson.repositories.ProductRepository;
import com.softuni.springjson.service.CategoryService;
import com.softuni.springjson.service.ProductService;
import com.softuni.springjson.service.UserService;
import com.softuni.springjson.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        if (this.productRepository.count() != 0) {
            return;
        }

        Arrays.stream(productSeedDtos)
                .forEach(productSeedDto -> {
                    if (this.validationUtil.isValid(productSeedDto)) {
                        Product product = this.modelMapper.map(productSeedDto, Product.class);

                        product.setSeller(this.userService.getRandomUser());

                        Random random = new Random();
                        int num = random.nextInt(2);

                        if (num == 0) {
                            product.setBuyer(this.userService.getRandomUser());
                        }

                        product.setCategories(new HashSet<>(this.categoryService.getRandomCategories()));

                        this.productRepository.saveAndFlush(product);
                    } else {
                        this.validationUtil.getViolations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<ProductInRangeDto> getAllProductsInRange() {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream()
                .map(p -> {
                    ProductInRangeDto productInRangeDto = this.modelMapper.map(p, ProductInRangeDto.class);

                    productInRangeDto
                            .setSeller(p.getSeller().getFirstName() + " " + p.getSeller().getLastName());

                    return productInRangeDto;
                })
                .collect(Collectors.toList());
    }
}
