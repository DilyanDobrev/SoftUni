package com.softuni.jsoncardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

public class SupplierViewDto {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    private Long partsCount;

    public SupplierViewDto() {
    }

    public SupplierViewDto(Long id, String name, Long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Long partsCount) {
        this.partsCount = partsCount;
    }
}
