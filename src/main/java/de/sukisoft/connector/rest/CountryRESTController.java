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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class CountryRESTController extends AbstractRestController {

	private final CountryService countryService;

	/**
	 * @param countryService to retrieve Countries.
	 */
	@Autowired
	public CountryRESTController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping
	@Tag(name = "countries")
	@Operation(summary = "Queries for all Countries together with Users", responses = {
			@ApiResponse(responseCode = "200", description = "The found Countries as response containing all Users.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountriesResponse.class))) })
	ResponseEntity<CountriesResponse> countryResponse() {
		return new ResponseEntity<>(countryService.getCountriesResponse(), HttpStatus.OK);
	}

	@GetMapping("/countries")
	@Tag(name = "countries")
	@Operation(summary = "Queries for all Countries", responses = { @ApiResponse(responseCode = "200", description = "The found Countries as List.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))) })
	ResponseEntity<List<CountryDTO>> findAllCountries() {
		return new ResponseEntity<>(countryService.findAllCountries(), HttpStatus.OK);
	}

}
