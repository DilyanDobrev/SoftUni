package com.softuni.xmlcardealer.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;
    private Boolean isImporter;

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer")
    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
