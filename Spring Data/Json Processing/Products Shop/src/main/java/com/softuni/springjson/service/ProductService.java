package com.softuni.springjson.service;

import com.softuni.springjson.models.dtos.taskdtos.ProductInRangeDto;
import com.softuni.springjson.models.dtos.seeddtos.ProductSeedDto;

import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> getAllProductsInRange();
}
