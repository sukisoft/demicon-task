package de.sukisoft.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.sukisoft.connector.db.Country;
import de.sukisoft.connector.db.CountryRepository;
import de.sukisoft.connector.db.User;
import de.sukisoft.connector.db.UserRepository;

/**
 * Persisting some testdata in the "test"-profile.
 *
 * @author wesselmann
 */
@Component
@Profile("test")
public class LocalTestDataSetup implements ApplicationRunner {

	private final UserRepository userRepository;
	private final CountryRepository countryRepository;

	@Autowired
	public LocalTestDataSetup(UserRepository userRepository, CountryRepository countryRepository) {
		this.userRepository = userRepository;
		this.countryRepository = countryRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		Country country = countryRepository.save(Country.builder().name("country-1").build());

		userRepository.save(User.builder().gender("male").firstName("user-1-first").lastName("user-1-last").email("user-1@user-1.com").country(country).build());
	}
}
