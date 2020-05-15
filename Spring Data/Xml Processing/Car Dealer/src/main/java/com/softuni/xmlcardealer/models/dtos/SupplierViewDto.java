package com.softuni.xmlcardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierViewDto {

    @SerializedName("Id")
    @XmlAttribute
    private Long id;

    @SerializedName("Name")
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
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
