package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.mapper.CarMapper;
import com.stage.rentalcar.repository.CarRepository;
import com.stage.rentalcar.specification.FreeCarSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

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
    public List<Car> getFreeCars(LocalDate start, LocalDate end) {
        return carRepository.findAll(FreeCarSpecification.getFreeCars(start, end));
    }

    @Override
    public void insOrUpCar(CarDTO carDTO) {
        if (carDTO.getId() == null) {
            carRepository.save(carMapper.fromDTOtoEntity(carDTO));
        } else {
            edit(carDTO);
        }
    }

    @Override
    public void edit(CarDTO carDTO) {
        if(getCarById(carDTO.getId())!=null){
            carRepository.save(carMapper.fromDTOtoEntity(carDTO));
        } else {
            throw new RuntimeException("L'entità non esiste");
        }
    }

    @Override
    public void delCar(Integer id) {
        carRepository.delete(carRepository.getById(id));
    }
}
