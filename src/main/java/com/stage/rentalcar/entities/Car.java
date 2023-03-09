package com.stage.rentalcar.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Car")
@Data
public class Car implements Serializable {

    @Id
    @Column(name = "carId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Column(name = "plate")
    private String plate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "reservationId", orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
