package com.stage.rentalcar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Reservation")
@Data
public class Reservation implements Serializable {

    @Id
    @Column(name = "reservationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="carId", referencedColumnName = "carId")
    private Car car;

}
