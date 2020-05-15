package com.softuni.xmlcardealer.models.dtos.seeddtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {

    @XmlAttribute(name = "name")
    @NotNull
    private String name;

    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
