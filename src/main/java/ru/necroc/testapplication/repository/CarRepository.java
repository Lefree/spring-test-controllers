package ru.necroc.testapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.necroc.testapplication.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
