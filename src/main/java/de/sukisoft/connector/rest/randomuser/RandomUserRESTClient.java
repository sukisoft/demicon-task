package de.sukisoft.connector.rest.randomuser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client Implementation to query the randomuser-API.
 *
 * @author wesselmann
 */
@Component
public class RandomUserRESTClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RandomUserRESTClient.class);

	@Value("${randomuser-api.url}")
	private String targetURL;

	private RestTemplate restTemplate;

	/**
	 * @param restTemplate the REST-Template to use.
	 */
	@Autowired
	public RandomUserRESTClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Fetches the given amount of Users from the webservice and provide the Response.
	 *
	 * @param amount the amount of Users to retrieve.
	 * @return the List of Users that were retrieved.
	 */
	public List<UserResponse> fetchUsers(int amount) {
		if (amount == 0) {
			amount = 1;
		}

		List<UserResponse> userResponses = new ArrayList<>();
		ResponseEntity<RandomUserResponse> webserviceResponse = restTemplate.getForEntity(targetURL + "?results=" + amount, RandomUserResponse.class);
		RandomUserResponse responseBody = webserviceResponse.getBody();
		if (webserviceResponse.getStatusCode().is2xxSuccessful() && responseBody != null) {
			userResponses = responseBody.getResults();
		} else {
			LOGGER.error("error connecting to randomuser API, please check URL and configuration");
		}
		return userResponses;
	}
}
