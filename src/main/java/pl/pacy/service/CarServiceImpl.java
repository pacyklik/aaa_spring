package pl.pacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pacy.entity.Car;
import pl.pacy.repo.CarRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by pacy on 2016-08-29.
 */
@Service
public class CarServiceImpl {

	//	@Autowired TODO
	CarRepository carRepository;

	public List<Car> all() {
		List<Car> cars = carRepository.findAll();
		for (Car car : cars) {
			// lombook :)
			String numerRej = car.getNumerRej();
		}
		return cars;
	}
}
