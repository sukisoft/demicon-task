package de.sukisoft.connector;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Validates the application-specific settings.
 *
 * @author wesselmann
 */
@Validated
@ConfigurationProperties(prefix = "randomuser-api")
@Getter
@Setter
public class ValidatedApplicationSettings {

	/**
	 * the target-url, not empty.
	 */
	@NotEmpty
	private String url;

	/**
	 * the amount of users, min=1, max = 100.
	 */
	@Min(value = 1)
	@Max(value = 100)
	private Integer userSize;

	/**
	 * the period to do the synchronization in seconds, min=10, max=3600
	 */
	@Min(value = 10)
	@Max(value = 3600)
	private Integer period;

}
