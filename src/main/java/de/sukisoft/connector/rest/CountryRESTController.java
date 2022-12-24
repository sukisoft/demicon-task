package de.sukisoft.connector.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sukisoft.connector.db.CountryService;
import de.sukisoft.connector.rest.response.CountriesResponse;
import de.sukisoft.connector.rest.response.CountryDTO;

@RestController
public class CountryRESTController extends AbstractRestController {

	private final CountryService countryService;

	@Autowired
	public CountryRESTController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping
	ResponseEntity<CountriesResponse> countryResponse() {
		return new ResponseEntity<>(countryService.getCountriesResponse(), HttpStatus.OK);
	}

	@GetMapping("/countries")
	ResponseEntity<List<CountryDTO>> findAllCountries() {
		return new ResponseEntity<>(countryService.findAllCountries(), HttpStatus.OK);
	}

}
