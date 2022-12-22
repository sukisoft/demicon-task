package de.sukisoft.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Runnable Spring-Boot-Application.
 *
 * @author wesselmann
 */
@SpringBootApplication
@EnableScheduling
@Configuration
@EnableConfigurationProperties(ValidatedApplicationSettings.class)
public class APIConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIConnectorApplication.class, args);
	}

}