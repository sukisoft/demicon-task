package de.sukisoft.connector.rest.randomuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Response-POJO for the UserResponse.
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
public class UserResponse {

	@ToString.Include
	private String gender;

	@ToString.Include
	private NameResponse name;

	@ToString.Include
	private LocationResponse location;

	@ToString.Include
	private String email;
}
