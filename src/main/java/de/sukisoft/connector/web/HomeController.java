package de.sukisoft.connector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import de.sukisoft.connector.db.Country;
import de.sukisoft.connector.db.CountryRepository;
import de.sukisoft.connector.db.UserRepository;

/**
 * Controller for the home-dashbaord.
 *
 * @author wesselmann
 */
@Controller
public class HomeController extends AbstractViewController {

	private final CountryRepository countryRepository;
	private final UserRepository userRepository;

	private Country chosenCountry;

	@Autowired
	public HomeController(CountryRepository countryRepository, UserRepository userRepository) {
		this.countryRepository = countryRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/home")
	public String main(Model model) {
		model.addAttribute("countries", countryRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		return "home";
	}

	@GetMapping(value = "search")
	public String showStudentBySurname(@RequestParam(value = "countryName", required = false) String countryName, Model model) {
		model.addAttribute("users", StringUtils.isEmptyOrWhitespace(countryName) ? userRepository.findAll() : userRepository.findByCountryName(countryName));
		model.addAttribute("countries", countryRepository.findAll());
		return "home";
	}
}
