package pl.pacy.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by marek.stachyra on 2016-08-26.
 */
@Data
@Builder
public class CarDTO {

	private String numerRej;
	private String silnik;

}
