package com.softuni.jsoncardealer.services;

import com.softuni.jsoncardealer.models.dtos.SaleDetailsViewDto;

import java.util.List;

public interface SaleService {

    void generateSales();

    List<SaleDetailsViewDto> getSalesDetails();
}
