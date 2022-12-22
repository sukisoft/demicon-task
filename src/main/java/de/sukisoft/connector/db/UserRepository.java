package de.sukisoft.connector.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-Repository offering access to the {@link User}s.
 *
 * @author wesselmann
 */
public interface UserRepository extends JpaRepository<User, Long> {

}