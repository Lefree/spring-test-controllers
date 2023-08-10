package ru.necroc.testapplication.service;

import java.util.NoSuchElementException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.necroc.testapplication.repository.CarRepository;


@Service
public class TestDbService {

    private final CarRepository carRepository;

    private final JdbcTemplate jdbcTemplate;

    public TestDbService(CarRepository carRepository, JdbcTemplate jdbcTemplate) {
        this.carRepository = carRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void testDbMethods(long id) {
        var carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("Element with id %d not found!", id));
        }
        var car = carOptional.get();
        car.setName("Lexus");
        carRepository.flush();
        String carNameFromJdbc = jdbcTemplate.queryForObject("select name from car where id = ?", new Object[] {id}, String.class);
        System.out.println(car.getName() + " || " + "Lexus" + " || " + carNameFromJdbc);
    }
}
