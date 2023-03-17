package com.stage.rentalcar.repository.specification;

import com.stage.rentalcar.entities.Car;
import com.stage.rentalcar.entities.Reservation;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FreeCarSpecification {
    public static Specification<Car> getFreeCars(LocalDate start, LocalDate end) {
        return (root, query, builder) -> {
            Subquery<Reservation> subquery = query.subquery(Reservation.class);
            Root<Reservation> subqueryRoot = subquery.from(Reservation.class);
            subquery.select(subqueryRoot.get("car").get("id"))
                    .where(builder.or(
                            builder.between(subqueryRoot.get("startDate"), start, end),
                            builder.between(subqueryRoot.get("endDate"), start, end),
                            builder.and(
                                    builder.lessThanOrEqualTo(subqueryRoot.get("startDate"), start),
                                    builder.greaterThanOrEqualTo(subqueryRoot.get("endDate"), end)
                            )
                    ));
            subquery.distinct(true);
            return builder.not(
                    builder.in(root.get("id")).value(subquery)
            );
        };
    }
}