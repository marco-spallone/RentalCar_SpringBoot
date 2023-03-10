package com.stage.rentalcar.controllers;

import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.services.CarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) { this.carService=carService; }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.getCars();
        if(cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Integer id){
        Car car = carService.getCarById(id);
        if(car==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<User> insertOrUpdateCar(@RequestBody Car car){
        carService.insOrUpCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Integer id){
        Car car = carService.getCarById(id);
        if(car==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        carService.delCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
