package de.sukisoft.connector.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity relating to a User which has been queried from the randomuser-API.
 *
 * @author wesselmann
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
@Entity
@Table(name = "user", indexes = @Index(name = "idx_user_name", columnList = "first_name"))
public class User {

	/**
	 * the technical identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@ToString.Include
	private Long id;

	/**
	 * the sex of the user.
	 */
	@Column(name = "gender", nullable = false, length = 255)
	@ToString.Include
	private String gender;

	/**
	 * the first-name of the user.
	 */
	@ToString.Include
	@Column(name = "first_name", nullable = false, length = 255)
	private String firstName;

	/**
	 * the last-name of the user.
	 */
	@ToString.Include
	@Column(name = "last_name", nullable = false, length = 255)
	private String lastName;

	/**
	 * the email of the user.
	 */
	@Column(name = "email", nullable = false, length = 255)
	private String email;

	/**
	 * the country in which the user is resided.
	 */
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	@ToString.Include
	private Country country;

}
