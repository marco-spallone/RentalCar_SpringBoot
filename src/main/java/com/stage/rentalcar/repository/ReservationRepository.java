package com.stage.rentalcar.repository;

import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> getByUser(User user);

    Reservation getById(Integer id);
}