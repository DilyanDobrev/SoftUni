package com.softuni.springjson.service;

import com.softuni.springjson.models.dtos.seeddtos.CategorySeedDto;
import com.softuni.springjson.models.dtos.taskdtos.CategoryProductsDto;
import com.softuni.springjson.models.entities.Category;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategories();

    List<CategoryProductsDto> getCategoriesByProductsCount();
}
