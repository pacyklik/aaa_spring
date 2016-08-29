package pl.pacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacy.entity.Car;

/**
 * Created by pacy on 2016-08-29.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
