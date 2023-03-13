package com.stage.rentalcar.services;

import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.getById(id);
    }

    @Override
    public void insOrUpCar(Car car) {
        carRepository.saveAndFlush(car);
    }

    @Override
    public void delCar(Car car) {
        carRepository.delete(car);
    }
}
