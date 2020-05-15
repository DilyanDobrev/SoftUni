package com.softuni.xmlproductshop.service.impl;

import com.softuni.xmlproductshop.models.dtos.seeddtos.CategorySeedDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.CategoryProductsDto;
import com.softuni.xmlproductshop.models.entities.Category;
import com.softuni.xmlproductshop.repositories.CategoryRepository;
import com.softuni.xmlproductshop.service.CategoryService;
import com.softuni.xmlproductshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categorySeedDtos) {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        categorySeedDtos
                .forEach(categorySeedDto -> {
                    if (this.validationUtil.isValid(categorySeedDto)) {
                        Category category = this.modelMapper.map(categorySeedDto, Category.class);
                        this.categoryRepository.saveAndFlush(category);
                    } else {
                        this.validationUtil.getViolations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<Category> getRandomCategories() {
        Random random = new Random();

        List<Category> result = new ArrayList<>();

        int rndCounter = random.nextInt(3) + 1;
        for (int i = 0; i < rndCounter; i++) {
            long rndId = random.nextInt((int) this.categoryRepository.count()) + 1;
            result.add(this.categoryRepository.getOne(rndId));
        }
        
        return result;
    }

    @Override
    public List<CategoryProductsDto> getCategoriesByProductsCount() {
       return this.categoryRepository.categoriesByProductsCount()
               .stream()
               .map(c -> {
                   CategoryProductsDto dto = new CategoryProductsDto();

                   dto.setName((String) c[0]);
                   dto.setProductsCount(((BigInteger) c[1]).intValue());
                   dto.setAveragePrice((BigDecimal) c[2]);
                   dto.setTotalRevenue((BigDecimal) c[3]);

                   return dto;
               })
               .collect(Collectors.toList());
    }
}
