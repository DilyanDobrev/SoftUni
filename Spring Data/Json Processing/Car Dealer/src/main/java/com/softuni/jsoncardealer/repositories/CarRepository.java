package com.softuni.jsoncardealer.repositories;

import com.softuni.jsoncardealer.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
