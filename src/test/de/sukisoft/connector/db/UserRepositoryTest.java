package de.sukisoft.connector.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Nested
	@DisplayName("findByCountryName-Tests")
	class findByNameTests {

		@Test
		@DisplayName("Users with 'country-1' are found.")
		void shouldFoundExistingUserByItsName() {
			List<User> byUserName = repository.findByCountryName("country-1");
			assertNotNull(byUserName, "no List found!");
			assertEquals(1, byUserName.size(), "Must find exactly one User");
		}

		@Test
		@DisplayName("not existing Country does not find any Users.")
		void shouldNotFindNotExistingUser() {
			List<User> byUserName = repository.findByCountryName("23u3a7Wbp1GFmDCDc7NX61Cs");
			assertNotNull(byUserName, "no List found!");
			assertEquals(0, byUserName.size(), "Must not find a User!");
		}
	}
}