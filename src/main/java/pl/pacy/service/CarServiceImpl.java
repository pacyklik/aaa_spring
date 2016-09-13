package pl.pacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pacy.dto.CarDTO;
import pl.pacy.entity.Car;
import pl.pacy.repo.CarRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pacy on 2016-08-29.
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public List<CarDTO> all() {
		List<CarDTO> cars = new ArrayList<>();
		for (Car car : carRepository.findAll()) {
			cars.add(CarDTO.builder().id(car.getId()).numerRej(car.getNumerRej()).silnik(car.getSilnik()).build());
		}
		return cars;
	}

	@Override public CarDTO save(CarDTO carDTO) {
		Car car = Car.builder()
				.numerRej(carDTO.getNumerRej())
				.silnik(carDTO.getSilnik())
				.build();
		car = carRepository.save(car);
		return CarDTO.builder().id(car.getId()).numerRej(car.getNumerRej()).silnik(car.getSilnik()).build();
	}

	@Override public List<CarDTO> delete(Long id) {
		carRepository.delete(id);
		return all();
	}
}
