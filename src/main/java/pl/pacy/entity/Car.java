package pl.pacy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Car {

	@Id
	private Long id;
	private String numerRej;
	private String silnik;
}
