package com.stage.rentalcar.repository;

import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> getByUser(User user);
    Reservation getById(Integer id);
    @Query("SELECT r FROM Reservation r WHERE (r.startDate BETWEEN :startDate AND :endDate) " +
            "OR (r.endDate BETWEEN :startDate AND :endDate)" +
            "OR (r.startDate <= :startDate AND r.endDate >= :endDate)")
    List<Reservation> getReservationsBetweenDates(@Param("startDate") LocalDate start, @Param("endDate") LocalDate end);
}