package pl.pacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pacy.dto.CarDTO;
import pl.pacy.service.CarService;

import java.util.List;
import java.util.Set;

/**
 * Created by pacy on 26.08.16.
 */
@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("car/{id}")
	public CarDTO car(@PathVariable Long id) {
		return carService.one(id);
	}

	@GetMapping("car")
	public List<CarDTO> cars() {
		return carService.all();
	}

	@PostMapping("car")
	public CarDTO newCar(@RequestBody CarDTO carDTO) {
		return carService.save(carDTO);
	}

	@PutMapping("car")
	public CarDTO updateCar(@RequestBody CarDTO carDTO) {
		return carService.save(carDTO);
	}

	@DeleteMapping("car/{id}")
	public List<CarDTO> deleteCar(@PathVariable Long id) {
		System.out.println("zz: " + id);
		return carService.delete(id);
	}
}
