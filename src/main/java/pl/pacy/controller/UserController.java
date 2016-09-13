package pl.pacy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pacy.dto.UserDTO;

/**
 * Created by pacy on 2016-09-09.
 */
@RestController
public class UserController {

	@PostMapping("/api/user")
	public UserDTO login(@RequestBody UserDTO userDTO) {

		if (userDTO.getUsername().equals("zzz") && userDTO.getPassword().equals("zzz")) {
			userDTO.setId(1L);
			return userDTO;
		} else {
			return new UserDTO();
		}
	}
}
