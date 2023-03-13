package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;

public class CarMapper {

    public Car fromDTOtoEntity(CarDTO carDTO){
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setPrice(carDTO.getPrice());
        car.setPlate(carDTO.getPlate());
        return car;
    }
}
