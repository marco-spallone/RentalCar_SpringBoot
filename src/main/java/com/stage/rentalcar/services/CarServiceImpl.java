package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.mapper.CarMapper;
import com.stage.rentalcar.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.getById(id);
    }

    @Override
    public void insOrUpCar(CarDTO carDTO) {
        carRepository.saveAndFlush(carMapper.fromDTOtoEntity(carDTO));
    }

    @Override
    public void delCar(Car car) {
        carRepository.delete(car);
    }
}
