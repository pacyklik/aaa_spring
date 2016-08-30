package pl.pacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacy.dto.CarDTO;
import pl.pacy.service.CarService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pacy on 26.08.16.
 */
@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/car")
	public Set<CarDTO> car() {
		return carService.all();
	}
}
