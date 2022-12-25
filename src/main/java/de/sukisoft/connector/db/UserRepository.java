package de.sukisoft.connector.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-Repository offering access to the {@link User}s.
 *
 * @author wesselmann
 */
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByCountryName(String name);
}