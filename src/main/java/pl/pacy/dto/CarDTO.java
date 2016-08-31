package pl.pacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by marek.stachyra on 2016-08-26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

	private Long id;
	private String numerRej;
	private String silnik;

}
