package pl.pacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pacy.dto.CarDTO;
import pl.pacy.entity.Car;
import pl.pacy.repo.CarRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pacy on 2016-08-29.
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public Set<CarDTO> all() {
		Set<CarDTO> cars = new HashSet<>();
		for (Car car : carRepository.findAll()) {
			// lombook :)
			cars.add(CarDTO.builder().numerRej(car.getNumerRej()).silnik(car.getSilnik()).build());
		}
		return cars;
	}

	@Override public void save(CarDTO carDTO) {
		Car car = Car.builder()
				.numerRej(carDTO.getNumerRej())
				.silnik(carDTO.getSilnik())
				.build();
		carRepository.save(car);
	}
}
