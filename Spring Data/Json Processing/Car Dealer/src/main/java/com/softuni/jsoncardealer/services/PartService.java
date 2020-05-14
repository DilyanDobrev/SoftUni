package com.softuni.jsoncardealer.services;

import com.softuni.jsoncardealer.models.dtos.seedDtos.PartSeedDto;
import com.softuni.jsoncardealer.models.entities.Part;

import java.util.List;

public interface PartService {

    void saveAll(PartSeedDto[] partSeedDtos);

    List<Part> getAllParts();
}
