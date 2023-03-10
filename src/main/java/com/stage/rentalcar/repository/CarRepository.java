package com.stage.rentalcar.repository;

import com.stage.rentalcar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByCarId(Integer id);
}
