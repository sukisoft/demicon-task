package de.sukisoft.connector.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing a Country which can have multiple Users.
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
@Table(name = "country", indexes = @Index(name = "idx_country_name", columnList = "name"),
		uniqueConstraints = { @UniqueConstraint(name = "uk_country_name", columnNames = "name") })
public class Country {

	/**
	 * the technical identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@ToString.Include
	private Long id;

	/**
	 * the country-name, e.g. "Germany".
	 */
	@ToString.Include
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	/**
	 * the list of Users that live in this country.
	 */
	@OneToMany(mappedBy = "country")
	@Builder.Default
	private List<User> users = new ArrayList<>();
}
