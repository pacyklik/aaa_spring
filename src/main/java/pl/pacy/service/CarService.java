package pl.pacy.service;

import pl.pacy.dto.CarDTO;

import java.util.Set;

/**
 * Created by pacy on 2016-08-30.
 */
public interface CarService {
	public Set<CarDTO> all();

	public CarDTO save(CarDTO carDTO);
}
