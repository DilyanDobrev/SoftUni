package com.softuni.xmlproductshop.repositories;

import com.softuni.xmlproductshop.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal low, BigDecimal high);

}
