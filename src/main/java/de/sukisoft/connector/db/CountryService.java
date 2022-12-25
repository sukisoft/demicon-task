package de.sukisoft.connector.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sukisoft.connector.rest.response.CountriesResponse;
import de.sukisoft.connector.rest.response.CountryDTO;
import de.sukisoft.connector.rest.response.UserDTO;

/**
 * Business-logic relating to Countries.
 *
 * @author wesselmann
 */
@Service
public class CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

	private final CountryRepository countryRepository;

	/**
	 * @param countryRepository to get countries
	 */
	@Autowired
	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	/**
	 * Queries a Country by its name. If no Country is found, it is created.
	 *
	 * @param countryName the country-name.
	 * @return the found or created {@link Country}.
	 */
	public Country getCountry(String countryName) {
		Optional<Country> byCountryName = Optional.ofNullable(countryRepository.findByName(countryName));
		return byCountryName.orElseGet(() -> {
			Country createdCountry = countryRepository.save(Country.builder().name(countryName).build());
			LOGGER.info("created {}", createdCountry);
			return createdCountry;
		});
	}

	/**
	 * @return queries for all Countries and parses the results to a list of {@link CountryDTO}s.
	 */
	public List<CountryDTO> findAllCountries() {
		return countryRepository.findAll().stream().map(c -> CountryDTO.builder().name(c.getName()).build()).collect(Collectors.toList());
	}

	/**
	 * @return creates the {@link CountriesResponse} for all currently synchronized data.
	 */
	public CountriesResponse getCountriesResponse() {
		List<Country> allCountries = countryRepository.findAll();

		CountriesResponse countriesResponse = CountriesResponse.builder().build();
		for (Country c : allCountries) {
			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setName(c.getName());
			for (User u : c.getUsers()) {
				UserDTO userDTO = UserDTO.builder().gender(u.getGender()).name(u.getFirstName() + " " + u.getLastName()).email(u.getEmail()).build();
				countryDTO.getUsers().add(userDTO);
			}
			countriesResponse.getCountries().add(countryDTO);
		}

		return countriesResponse;
	}
}
