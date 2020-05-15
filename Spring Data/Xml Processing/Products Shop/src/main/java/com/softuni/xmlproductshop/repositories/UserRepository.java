package com.softuni.xmlproductshop.repositories;

import com.softuni.xmlproductshop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u " +
            "JOIN Product p ON u.id = p.seller.id " +
            "WHERE p.buyer.id IS NOT NULL " +
            "ORDER BY u.lastName, u.firstName")
    Set<User> findAllUsersSoldAtLeastOneProduct();

    @Query(value = "SELECT u FROM User u " +
            "JOIN Product p ON u.id = p.seller.id " +
            "WHERE p.buyer.id IS NOT NULL " +
            "ORDER BY u.sold.size DESC, u.lastName")
    Set<User> findAllUsersWithSortedSoldProducts();

}
