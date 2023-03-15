package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public List<CarDTO> getCarsDTO(List<Car> cars) {
        return cars.stream().map(this::fromEntitytoDTO).collect(Collectors.toList());
    }

    public Car fromDTOtoEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setPrice(carDTO.getPrice());
        car.setPlate(carDTO.getPlate());
        return car;
    }

    public CarDTO fromEntitytoDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setPrice(car.getPrice());
        carDTO.setPlate(car.getPlate());
        return carDTO;
    }
}
