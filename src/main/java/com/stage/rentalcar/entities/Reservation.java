package com.stage.rentalcar.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data
public class Reservation implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="car", referencedColumnName = "id")
    private Car car;
}
