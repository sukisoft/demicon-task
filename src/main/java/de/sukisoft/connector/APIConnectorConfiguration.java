package de.sukisoft.connector;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 * Configures the application with specific Beans to use.
 *
 * @author wesselmann
 */
@Configuration
public class APIConnectorConfiguration {

	/**
	 * Creates a REST-Template with a read- and connect-timeout of 5 seconds.
	 *
	 * @param restTemplateBuilder to create the Template
	 * @return the created Template.
	 */
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		Duration fiveSeconds = Duration.of(5, ChronoUnit.SECONDS);
		return restTemplateBuilder.setConnectTimeout(fiveSeconds).setReadTimeout(fiveSeconds).build();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		return messageSource;
	}

}
