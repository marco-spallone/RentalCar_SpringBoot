package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> getCars();
    Car getCarById(Integer id);
    List<Car> getFreeCars(LocalDate start, LocalDate end);
    void insOrUpCar(CarDTO carDTO);
    void edit(CarDTO carDTO);
    void delCar(Integer id);
}
