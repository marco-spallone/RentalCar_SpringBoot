package com.stage.rentalcar.services;

import com.stage.rentalcar.entities.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getReservations();
    public Reservation getReservationById(Integer id);
    public void insOrUpReservation(Reservation reservation);
    public void delReservation(Reservation reservation);
}
