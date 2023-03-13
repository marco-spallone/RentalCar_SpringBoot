package com.stage.rentalcar.controllers;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.mapper.ReservationMapper;
import com.stage.rentalcar.services.ReservationService;
import com.stage.rentalcar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final ReservationMapper reservationMapper;

    @GetMapping(value = "/user/{id}", produces = "application/json")                //PROVVISORIO, QUANDO SI HA L'UTENTE IN SESSIONE ELIMINARE PARAMETRO
    public ResponseEntity<List<ReservationDTO>> getAllReservationsForUser(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(reservationMapper.getReservationsDTO(reservationService.getReservationsForUser(user)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(reservationService.getReservationById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> insertOrUpdateReservation(@RequestBody ReservationDTO reservationDTO){
        reservationService.insOrUpReservation(reservationDTO);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Integer id){
        reservationService.delReservation(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}