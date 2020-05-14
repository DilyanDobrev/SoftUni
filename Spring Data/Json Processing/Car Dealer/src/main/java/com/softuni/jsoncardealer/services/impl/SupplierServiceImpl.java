package com.softuni.jsoncardealer.services.impl;

import com.softuni.jsoncardealer.models.dtos.SupplierViewDto;
import com.softuni.jsoncardealer.models.dtos.seedDtos.SupplierSeedDto;
import com.softuni.jsoncardealer.models.entities.Supplier;
import com.softuni.jsoncardealer.repositories.SupplierRepository;
import com.softuni.jsoncardealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository,
                                ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(SupplierSeedDto[] supplierSeedDtos) {
        if (this.supplierRepository.count() != 0) {
            return;
        }
        Supplier[] suppliers = this.modelMapper.map(supplierSeedDtos, Supplier[].class);
        this.supplierRepository.saveAll(Arrays.asList(suppliers));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return this.supplierRepository.findAll();
    }

    @Override
    public List<SupplierViewDto> getLocalSuppliers() {
        return this.supplierRepository.getLocalSuppliers()
                .stream()
                .map(supplier -> {
                    SupplierViewDto supplierDto = this.modelMapper.map(supplier, SupplierViewDto.class);
                    supplierDto.setId((Long) supplier[0]);
                    supplierDto.setName((String) supplier[1]);
                    supplierDto.setPartsCount((Long) supplier[2]);

                    return supplierDto;
                })
                .collect(Collectors.toList());
    }
}
