package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsForUser(User user);
    ReservationDTO getReservationById(Integer id);
    void insOrUpReservation(ReservationDTO reservationDTO);
    void delReservation(Integer id);
}
