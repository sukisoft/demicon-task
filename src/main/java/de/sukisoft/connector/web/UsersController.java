package de.sukisoft.connector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.sukisoft.connector.db.UserRepository;

@Controller
public class UsersController extends AbstractViewController {

	private final UserRepository userRepository;

	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public String view(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
}
