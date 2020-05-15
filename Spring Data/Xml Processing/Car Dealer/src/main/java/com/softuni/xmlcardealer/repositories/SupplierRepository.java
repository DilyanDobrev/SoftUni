package com.softuni.xmlcardealer.repositories;

import com.softuni.xmlcardealer.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s.id, s.name, COUNT(s) " +
            "FROM Supplier AS s " +
            "JOIN Part AS p " +
            "ON s.id = p.supplier.id " +
            "WHERE s.importer = false " +
            "GROUP BY s.id")
    List<Object[]> getLocalSuppliers();
}
