package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsForUser(User user);
    ReservationDTO getReservationById(Integer id);
    List<Reservation> getReservationsBetweenDates(LocalDate start, LocalDate end);
    List<Car> getFreeCars(ReservationDTO reservationDTO) throws Exception;
    void insOrUpReservation(ReservationDTO reservationDTO);
    void approveReservation(Integer id);
    void declineReservation(Integer id);
    void delReservation(Integer id) throws Exception;
}
