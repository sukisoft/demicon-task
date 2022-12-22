package de.sukisoft.connector.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class CountryRepositoryTest {

	@Autowired
	private CountryRepository repository;

	@Nested
	@DisplayName("findByName-Tests")
	class findByNameTests {

		@Test
		@DisplayName("'country-1' is found.")
		void when_existingApplicationIsGiven_then_itIsFound() {
			Optional<Country> byCountryName = repository.findByName("country-1");
			assertTrue(byCountryName.isPresent(), "country-1 not found!");
			assertEquals("country-1", byCountryName.get().getName(), "Name does not match!");
		}

		@Test
		@DisplayName("not existing Country is not found.")
		void when_notExistingCountryIsGiven_then_itIsFound() {
			Optional<Country> byCountryName = repository.findByName("QCyvt");
			assertFalse(byCountryName.isPresent(), "Country must not exist!");
		}
	}
}