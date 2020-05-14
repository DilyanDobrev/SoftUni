package com.softuni.jsoncardealer.services;

import com.softuni.jsoncardealer.models.dtos.SupplierViewDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.SupplierSeedDto;
import com.softuni.jsoncardealer.models.entities.Supplier;

import java.util.List;

public interface SupplierService {

    void saveAll(SupplierSeedDto[] supplierSeedDtos);

    List<Supplier> getAllSuppliers();

    List<SupplierViewDto> getLocalSuppliers();
}
