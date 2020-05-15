package com.softuni.xmlcardealer.services;


import com.softuni.xmlcardealer.models.dtos.SaleDetailsViewDto;

import java.util.List;

public interface SaleService {

    void generateSales();

    List<SaleDetailsViewDto> getSalesDetails();
}
