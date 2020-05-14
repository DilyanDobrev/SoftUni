package com.softuni.springjson.repositories;

import com.softuni.springjson.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.name, COUNT(p.id) as 'product_count',\n" +
            "            AVG(p.price) as 'average_price',\n" +
            "            SUM(p.price) as 'total_revenue'\n" +
            "            FROM categories as c\n" +
            "            JOIN products_categories as pc on c.id = pc.categories_id\n" +
            "            JOIN products p on pc.products_id = p.id\n" +
            "            GROUP BY c.name\n" +
            "            ORDER BY product_count DESC;", nativeQuery = true)
    Set<Object[]> categoriesByProductsCount();
}



//    SELECT c.name, COUNT(p.id) as 'product_count', " +
//        "AVG(p.price) as 'average_price', " +
//        "SUM(p.price) as 'total_revenue' " +
//        "FROM categories c " +
//        "JOIN products_categories pc on c.id = pc.category_id " +
//        "JOIN products p on pc.product_id = p.id " +
//        "GROUP BY c.name " +
//        "ORDER BY product_count DESC", nativeQuery = true)