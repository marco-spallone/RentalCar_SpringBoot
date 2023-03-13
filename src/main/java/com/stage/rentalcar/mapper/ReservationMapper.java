package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public List<ReservationDTO> getReservationsDTO(List<Reservation> reservations){
        return reservations.stream().map(this::fromEntitytoDTO).collect(Collectors.toList());
    }

    public Reservation fromDTOtoEntity(ReservationDTO reservationDTO, User user, Car car) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setConfirmed(false);
        reservation.setUser(user);
        reservation.setCar(car);
        return reservation;
    }

    public ReservationDTO fromEntitytoDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setCarId(reservation.getCar().getId());
        reservationDTO.setUserId(reservation.getUser().getId());
        return reservationDTO;
    }

}
