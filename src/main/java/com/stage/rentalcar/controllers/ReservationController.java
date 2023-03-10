package com.stage.rentalcar.controllers;

import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.services.CarService;
import com.stage.rentalcar.services.ReservationService;
import com.stage.rentalcar.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService, UserService userService) { this.reservationService=reservationService;}

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = reservationService.getReservations();
        if(reservations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable("id") Integer id){
        Reservation reservation = reservationService.getReservationById(id);
        if(reservation==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<Reservation> insertOrUpdateReservation(@RequestBody Reservation reservation){
        reservationService.insOrUpReservation(reservation);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Integer id){
        Reservation reservation = reservationService.getReservationById(id);
        if(reservation==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        reservationService.delReservation(reservation);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
