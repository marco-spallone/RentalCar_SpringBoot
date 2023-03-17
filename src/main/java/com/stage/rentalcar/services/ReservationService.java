package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.dto.request.FreeCarRequest;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsForUser(User user);

    ReservationDTO getReservationDTOById(Integer id);

    List<Car> getFreeCars(FreeCarRequest freeCarRequest);

    void insOrUpReservation(ReservationDTO reservationDTO);

    void updateReservation(Integer id, boolean confirmed);

    void delReservation(Integer id);
}
