package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.ReservationDTO;
import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.mapper.ReservationMapper;
import com.stage.rentalcar.repository.CarRepository;
import com.stage.rentalcar.repository.ReservationRepository;
import com.stage.rentalcar.repository.UserRepository;
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
    public ReservationDTO getReservationById(Integer id) {
        return reservationMapper.fromEntitytoDTO(reservationRepository.getById(id));
    }

    @Override
    public List<Reservation> getReservationsBetweenDates(LocalDate start, LocalDate end) {
        return reservationRepository.getReservationsBetweenDates(start, end);
    }

    @Override
    public List<Car> getFreeCars(ReservationDTO reservationDTO) throws Exception {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), reservationDTO.getStartDate());
        if(days>2){
            List<Reservation> resBetweenDates = getReservationsBetweenDates(reservationDTO.getStartDate(), reservationDTO.getEndDate());
            return carService.getFreeCars(resBetweenDates);
        } else {
            throw new Exception("Mancano meno di 2 giorni alla data di inizio.");
        }
    }

    @Override
    public void insOrUpReservation(ReservationDTO reservationDTO) {
        User user = userRepository.getById(reservationDTO.getUserId());
        Car car = carRepository.getById(reservationDTO.getCarId());
        reservationRepository.saveAndFlush(reservationMapper.fromDTOtoEntity(reservationDTO, user, car));
    }

    @Override
    public void approveReservation(Integer id) {
        ReservationDTO reservationDTO = getReservationById(id);
        Reservation reservation = reservationMapper.fromDTOtoEntity(reservationDTO, userRepository.getById(reservationDTO.getUserId()),
                                carRepository.getById(reservationDTO.getCarId()));
        reservation.setConfirmed(true);
        reservationRepository.saveAndFlush(reservation);
    }

    @Override
    public void declineReservation(Integer id) {
        ReservationDTO reservationDTO = getReservationById(id);
        Reservation reservation = reservationMapper.fromDTOtoEntity(reservationDTO, userRepository.getById(reservationDTO.getUserId()),
                carRepository.getById(reservationDTO.getCarId()));
        reservation.setConfirmed(false);
        reservationRepository.saveAndFlush(reservation);
    }

    @Override
    public void delReservation(Integer id) throws Exception {
        ReservationDTO reservationDTO = getReservationById(id);
        long days = ChronoUnit.DAYS.between(LocalDate.now(), reservationDTO.getStartDate());
        if(days>2){
            reservationRepository.deleteById(id);
        } else {
            throw new Exception("Mancano meno di due giorni alla data di inizio.");
        }
    }
}
