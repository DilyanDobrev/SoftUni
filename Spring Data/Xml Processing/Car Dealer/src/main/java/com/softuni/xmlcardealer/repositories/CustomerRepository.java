package com.softuni.xmlcardealer.repositories;
import com.softuni.xmlcardealer.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.youngDriver")
    List<Customer> getAllByBirthdate();
}
