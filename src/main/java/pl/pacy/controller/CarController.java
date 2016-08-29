package pl.pacy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacy.dto.CarDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pacy on 26.08.16.
 */
@RestController
public class CarController {

    @GetMapping("/car")
    public Set<CarDTO> car() {
        Set<CarDTO> carDTOs = new HashSet<>();

        carDTOs.add(CarDTO.builder().numerRej("LPU45454").silnik("1.4").build());
        carDTOs.add(CarDTO.builder().numerRej("LSW20203").silnik("1.8").build());
        carDTOs.add(CarDTO.builder().numerRej("LU18181").silnik("2.4").build());
        carDTOs.add(CarDTO.builder().numerRej("LPU454224").silnik("1.4").build());

        return carDTOs;
    }
}
