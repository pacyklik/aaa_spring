package pl.pacy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pacy.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pacy on 2016-09-14.
 */
@RestController
public class BookControler {

	// /api/books/categories
	@GetMapping("/api/books/categories")
	public List categories() {
		List categories = new ArrayList<>();
		categories.add("HORROR");
		categories.add("FANTASY");
		categories.add("DRAMAT");
		categories.add("COMEDY");
		categories.add("THRILLER");
		categories.add("SCIENCE");
		categories.add("CRIMINAL");

		return categories;
	}

}
