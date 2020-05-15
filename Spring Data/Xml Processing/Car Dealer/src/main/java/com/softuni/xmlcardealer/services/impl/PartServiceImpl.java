package com.softuni.xmlcardealer.services.impl;

import com.softuni.xmlcardealer.models.dtos.seeddtos.PartSeedDto;
import com.softuni.xmlcardealer.models.entities.Part;
import com.softuni.xmlcardealer.models.entities.Supplier;
import com.softuni.xmlcardealer.repositories.PartRepository;
import com.softuni.xmlcardealer.services.PartService;
import com.softuni.xmlcardealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    @Autowired
    public PartServiceImpl(PartRepository partRepository,
                           ModelMapper modelMapper,
                           SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public void saveAll(List<PartSeedDto> partSeedDtos) {
        if (this.partRepository.count() != 0) {
            return;
        }
        Part[] parts = this.modelMapper.map(partSeedDtos, Part[].class);

        Random random = new Random();
        List<Supplier> allSuppliers = this.supplierService.getAllSuppliers();
        for (Part part : parts) {
            part.setSupplier(allSuppliers.get(random.nextInt(allSuppliers.size())));
        }

        this.partRepository.saveAll(Arrays.asList(parts));
    }

    @Override
    public List<Part> getAllParts() {
        return this.partRepository.findAll();
    }
}
