package de.sukisoft.connector.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-Repository offering access to the Countries.
 *
 * @author wesselmann
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

	/**
	 * @param name the country-name.
	 * @return the optionally found country.
	 */
	Country findByName(String name);
}
