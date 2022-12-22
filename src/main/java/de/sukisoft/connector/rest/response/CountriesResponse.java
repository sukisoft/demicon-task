package de.sukisoft.connector.rest.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Response-POJO for the CountriesResponse.
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
public class CountriesResponse {

	@ToString.Include
	@Builder.Default
	private List<CountryDTO> countries = new ArrayList<>();
}
