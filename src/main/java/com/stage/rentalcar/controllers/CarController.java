package com.stage.rentalcar.controllers;

import com.stage.rentalcar.dto.CarDTO;
import com.stage.rentalcar.mapper.CarMapper;
import com.stage.rentalcar.dto.request.FreeCarRequest;
import com.stage.rentalcar.services.CarService;
import com.stage.rentalcar.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final ReservationService reservationService;
    private final CarMapper carMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return new ResponseEntity<>(carMapper.getCarsDTO(carService.getCars()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(carMapper.fromEntitytoDTO(carService.getCarById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/free-cars", produces = "application/json")
    public ResponseEntity<List<CarDTO>> getFreeCars(@RequestBody FreeCarRequest freeCarRequest) {
        return new ResponseEntity<>(carMapper.getCarsDTO(reservationService.getFreeCars(freeCarRequest)), HttpStatus.OK);
    }


    @PostMapping(value = "/post-car", produces = "application/json")
    public ResponseEntity<?> insertOrUpdateCar(@RequestBody CarDTO carDTO) {
        carService.insOrUpCar(carDTO);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Integer id) {
        carService.delCar(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
