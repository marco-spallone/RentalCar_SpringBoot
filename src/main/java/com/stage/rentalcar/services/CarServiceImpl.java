package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.mapper.CarMapper;
import com.stage.rentalcar.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<Car> getFreeCars(List<Reservation> reservations) {
        List<Car> free = getCars();
        List<Car> alreadyReserved = new ArrayList<>();
        for (Reservation r:reservations) {
            alreadyReserved.add(r.getCar());
        }
        Iterator<Car> iterOnFree = free.iterator();
        Iterator<Car> iterOnReserved = alreadyReserved.iterator();
        while (iterOnFree.hasNext()) {
            Car car1 = iterOnFree.next();
            while (iterOnReserved.hasNext()) {
                Car car2 = iterOnReserved.next();
                if (car1.getId().equals(car2.getId())) {
                    iterOnFree.remove();
                    break;
                }
            }
        }
        return free;
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
