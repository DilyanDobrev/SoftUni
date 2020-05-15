package com.softuni.xmlcardealer.services;


import com.softuni.xmlcardealer.models.dtos.seeddtos.PartSeedDto;
import com.softuni.xmlcardealer.models.entities.Part;

import java.util.List;

public interface PartService {

    void saveAll(List<PartSeedDto> partSeedDtos);

    List<Part> getAllParts();
}
