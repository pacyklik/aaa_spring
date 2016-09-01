package pl.pacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pacy.dto.CarDTO;
import pl.pacy.service.CarService;

import java.util.Set;

/**
 * Created by pacy on 26.08.16.
 */
@RestController
//@RequestMapping("/api/cars")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("car")
	public Set<CarDTO> car() {
		return carService.all();
	}

	@PostMapping("car")//("/{id:^[0-9]+$}")
	public CarDTO newCar(@RequestBody CarDTO carDTO) {
		//		carService.save(carDTO);
		return carService.save(carDTO);
	}

	@DeleteMapping("car/{id}")
	public void deleteCar(@PathVariable Long id) {
		System.out.println("zz: " + id);
	}
}
