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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    @Override
    public List<Reservation> getReservationsForUser(User user) {
        return reservationRepository.getByUser(user);
    }

    @Override
    public ReservationDTO getReservationById(Integer id) {
        return reservationMapper.fromEntitytoDTO(reservationRepository.getById(id));
    }

    @Override
    public void insOrUpReservation(ReservationDTO reservationDTO) {
        User user = userRepository.getById(reservationDTO.getUserId());
        Car car = carRepository.getById(reservationDTO.getCarId());
        reservationRepository.saveAndFlush(reservationMapper.fromDTOtoEntity(reservationDTO, user, car));
    }

    @Override
    public void delReservation(Integer id) {
        reservationRepository.deleteById(id);
    }
}
