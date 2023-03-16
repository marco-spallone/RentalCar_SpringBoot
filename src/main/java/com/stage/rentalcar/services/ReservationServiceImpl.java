package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.mapper.ReservationMapper;
import com.stage.rentalcar.repository.CarRepository;
import com.stage.rentalcar.repository.ReservationRepository;
import com.stage.rentalcar.repository.UserRepository;
import com.stage.rentalcar.request.FreeCarRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Override
    public List<Reservation> getReservationsForUser(User user) {
        return reservationRepository.getByUser(user);
    }

    @Override
    public ReservationDTO getReservationDTOById(Integer id) {
        return reservationMapper.fromEntitytoDTO(reservationRepository.getById(id));
    }

    @Override
    public List<Car> getFreeCars(FreeCarRequest freeCarRequest) {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), freeCarRequest.getStartDate());
        if (days > 2) {
            return carService.getFreeCars(freeCarRequest.getStartDate(), freeCarRequest.getEndDate());
        } else {
            throw new RuntimeException("Mancano meno di 2 giorni alla data di inizio.");
        }
    }

    @Override
    public void insOrUpReservation(ReservationDTO reservationDTO) {
        User user = userRepository.getById(reservationDTO.getUserId());
        Car car = carRepository.getById(reservationDTO.getCarId());
        reservationRepository.save(reservationMapper.fromDTOtoEntity(reservationDTO, user, car));
    }

    @Override
    public void updateReservation(Integer id, boolean confirmed) {
        Reservation reservation = reservationRepository.getById(id);
        reservation.setConfirmed(confirmed);
        reservationRepository.save(reservation);
    }

    @Override
    public void delReservation(Integer id) {
        if (ChronoUnit.DAYS.between(LocalDate.now(), getReservationDTOById(id).getStartDate()) > 2) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Mancano meno di due giorni alla data di inizio.");
        }
    }
}
