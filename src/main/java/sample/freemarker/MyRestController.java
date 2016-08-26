package sample.freemarker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.freemarker.Car;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by marek.stachyra on 2016-08-26.
 */
@RestController
public class MyRestController {

	@RequestMapping("/car")
	public Set<Car> car() {
		Set<Car> cars = new HashSet<>();
		cars.add(new Car("LPU45454", "1.4"));
		cars.add(new Car("LSW20203", "1.8"));
		cars.add(new Car("LU18181", "2.4"));
		cars.add(new Car("LPU454224", "1.4"));

		return cars;
	}
}
