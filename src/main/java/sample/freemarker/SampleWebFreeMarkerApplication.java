/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.pacy.entity.Car;
import pl.pacy.repo.CarRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@EntityScan("pl.pacy.entity")
@EnableJpaRepositories(basePackages = "pl.pacy.repo")
@SpringBootApplication(scanBasePackages = { "pl.pacy" })
public class SampleWebFreeMarkerApplication {

	@Autowired
	private CarRepository carRepository;

	@PostConstruct
	@Transactional
	public void start() {
		Set<Car> cars = new HashSet<>();
		Car c = new Car();
		cars.add(Car.builder().numerRej("LPU45454").silnik("1.4").build());
		cars.add(Car.builder().numerRej("LSW20203").silnik("3.8").build());
		cars.add(Car.builder().numerRej("LU18181").silnik("2.4").build());
		cars.add(Car.builder().numerRej("LPU45454").silnik("1.4").build());
		carRepository.save(cars);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebFreeMarkerApplication.class, args);
	}

}
