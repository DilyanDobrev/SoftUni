package com.softuni.xmlcardealer.services;


import com.softuni.xmlcardealer.models.dtos.SupplierViewDto;
import com.softuni.xmlcardealer.models.dtos.seeddtos.SupplierSeedDto;
import com.softuni.xmlcardealer.models.entities.Supplier;

import java.util.List;

public interface SupplierService {

    void saveAll(List<SupplierSeedDto> supplierSeedDtos);

    List<Supplier> getAllSuppliers();

    List<SupplierViewDto> getLocalSuppliers();
}
