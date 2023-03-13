package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;

import java.util.List;

public interface CarService {
    List<Car> getCars();
    Car getCarById(Integer id);
    List<Car> getFreeCars(List<Reservation> reservations);
    void insOrUpCar(CarDTO carDTO);
    void delCar(Car car);
}
