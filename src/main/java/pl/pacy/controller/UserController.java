package pl.pacy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacy.dto.UserDTO;

import java.security.Principal;

/**
 * Created by pacy on 2016-09-09.
 */
@RestController
public class UserController {

	@RequestMapping("/api/user")
	public Principal user(Principal user) {
		return user;
	}

//	@PostMapping("/api/user")
//	public UserDTO login(@RequestBody UserDTO userDTO) {
//
//		if (userDTO.getUsername().equals("a") && userDTO.getPassword().equals("a")) {
//			userDTO.setId(1L);
//			return userDTO;
//		} else {
//			return new UserDTO();
//		}
//	}
}
