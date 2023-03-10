package com.stage.rentalcar.repository;

import com.stage.rentalcar.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByReservationId(Integer id);
}
