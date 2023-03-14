package com.stage.rentalcar.specification;

import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FreeCarSpecification {
    public static Specification<Car> getFreeCars(LocalDate start, LocalDate end){

        return (root, query, criteriaBuilder) -> {
            Join<Reservation, Car> tableJoin = root.join("reservations");
            Predicate firstPredicate = criteriaBuilder.between(tableJoin.get("startDate"), start, end);
            Predicate secondPredicate = criteriaBuilder.between(tableJoin.get("endDate"), start, end);
            Predicate thirdPredicate = criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(tableJoin.get("startDate"), start),
                    criteriaBuilder.greaterThanOrEqualTo(tableJoin.get("endDate"), end));
            query.distinct(true);
            Predicate finalPredicate = criteriaBuilder.and(firstPredicate, secondPredicate, thirdPredicate);
            return criteriaBuilder.not(finalPredicate);
        };
    }
}
