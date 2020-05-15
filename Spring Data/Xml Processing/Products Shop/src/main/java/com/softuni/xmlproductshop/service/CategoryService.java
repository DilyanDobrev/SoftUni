package com.softuni.xmlproductshop.service;

import com.softuni.xmlproductshop.models.dtos.seeddtos.CategorySeedDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.CategoryProductsDto;
import com.softuni.xmlproductshop.models.entities.Category;

import java.util.List;

public interface CategoryService {

    void seedCategories(List<CategorySeedDto> categorySeedDtos);

    List<Category> getRandomCategories();

    List<CategoryProductsDto> getCategoriesByProductsCount();
}
