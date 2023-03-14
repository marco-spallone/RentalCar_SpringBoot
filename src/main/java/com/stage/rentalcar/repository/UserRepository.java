package com.stage.rentalcar.repository;

import com.stage.rentalcar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(Integer id);
    List<User> getUsersByIsAdmin(boolean admin);
}
