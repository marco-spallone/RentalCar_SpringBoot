package com.stage.rentalcar.services;

import com.stage.rentalcar.entities.Car;

import java.util.List;

public interface CarService {
    public List<Car> getCars();
    public Car getCarById(Integer id);
    public void insOrUpCar(Car car);
    public void delCar(Car car);
}
