package pl.pacy.service;

import pl.pacy.dto.CarDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by pacy on 2016-08-30.
 */
public interface CarService {
	public CarDTO one(Long id);

	public List<CarDTO> all();

	public CarDTO save(CarDTO carDTO);

	public List<CarDTO> delete(Long id);
}
