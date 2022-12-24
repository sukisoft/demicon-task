package de.sukisoft.connector.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.sukisoft.connector.db.CountryRepository;

@Controller
public class CountriesController extends AbstractViewController {

	private final CountryRepository countryRepository;

	@Autowired
	public CountriesController(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@GetMapping("/countries")
	public String view(Model model) {
		model.addAttribute("countries", countryRepository.findAll());
		return "countries";
	}
}
