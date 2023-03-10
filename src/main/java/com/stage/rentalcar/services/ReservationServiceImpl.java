package com.stage.rentalcar.services;

import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.repository.CarRepository;
import com.stage.rentalcar.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findByReservationId(id);
    }

    @Override
    public void insOrUpReservation(Reservation reservation) {
        reservationRepository.saveAndFlush(reservation);
    }

    @Override
    public void delReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
