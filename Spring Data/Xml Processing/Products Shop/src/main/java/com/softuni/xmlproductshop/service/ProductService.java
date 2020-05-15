package com.softuni.xmlproductshop.service;

import com.softuni.xmlproductshop.models.dtos.seeddtos.ProductSeedDto;
import com.softuni.xmlproductshop.models.dtos.taskdtos.ProductInRangeDto;

import java.util.List;

public interface ProductService {

    void seedProducts(List<ProductSeedDto> productSeedDtos);

    List<ProductInRangeDto> getAllProductsInRange();
}
