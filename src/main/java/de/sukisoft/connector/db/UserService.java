package de.sukisoft.connector.db;

import static java.util.Objects.requireNonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sukisoft.connector.rest.randomuser.UserResponse;

/**
 * Business-logic relating to {@link User}s.
 *
 * @author wesselmann
 */
@Service
public class UserService {

	private final UserRepository userRepository;
	private final CountryService countryService;

	/**
	 * @param userRepository to get/save Users.
	 * @param countryService to get/save Countries.
	 */
	@Autowired
	public UserService(UserRepository userRepository, CountryService countryService) {
		this.userRepository = userRepository;
		this.countryService = countryService;
	}

	/**
	 * Creates a new {@link User} from the given Webservice-Response-Object.
	 *
	 * @param userResponse the raw-information to create the user of.
	 * @return the created {@link User}.
	 */
	public User createUser(UserResponse userResponse) {
		requireNonNull(userResponse);

		String countryName = userResponse.getLocation().getCountry();
		Country country = countryService.getCountry(countryName);

		User userToSave = User.builder()
				.gender(userResponse.getGender())
				.firstName(userResponse.getName().getFirst())
				.lastName(userResponse.getName().getLast())
				.email(userResponse.getEmail())
				.country(country)
				.build();
		return userRepository.save(userToSave);
	}
}
