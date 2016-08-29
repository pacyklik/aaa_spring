package sample.freemarker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pacy on 26.08.16.
 */
@RestController
public class CarController {

    @GetMapping("/car")
    public Set<Car> car() {
        Set<Car> cars = new HashSet<>();
        cars.add(new Car("aaa","111"));
        cars.add(new Car("bbb","222"));
        cars.add(new Car("ccc","333"));
        cars.add(new Car("aaa","111"));

        return cars;
    }
}
