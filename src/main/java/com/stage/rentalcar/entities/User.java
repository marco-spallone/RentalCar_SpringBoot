package com.stage.rentalcar.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User implements Serializable {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String nome;

    @Column(name = "surname")
    private String surname;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
