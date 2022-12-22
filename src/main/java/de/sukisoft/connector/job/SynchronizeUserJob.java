package de.sukisoft.connector.job;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.sukisoft.connector.db.User;
import de.sukisoft.connector.db.UserService;
import de.sukisoft.connector.rest.randomuser.RandomUserRESTClient;
import de.sukisoft.connector.rest.randomuser.UserResponse;

/**
 * Scheduled Job that does the synchronization.
 *
 * @author wesselmann
 */
@Service
public class SynchronizeUserJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizeUserJob.class);

	private final Integer userAmount;
	private final RandomUserRESTClient randomUserRESTClient;
	private final UserService userService;

	/**
	 * @param randomUserRESTClient to query the webservice.
	 * @param userAmount the amount of Users that will be queried.
	 * @param userService to created Users.
	 */
	@Autowired
	public SynchronizeUserJob(RandomUserRESTClient randomUserRESTClient, @Value("${randomuser-api.userSize}") Integer userAmount, UserService userService) {
		this.randomUserRESTClient = randomUserRESTClient;
		this.userAmount = userAmount;
		this.userService = userService;
	}

	/**
	 * Synchronizes an amount of Users with the internal database.
	 */
	@Scheduled(fixedDelayString = "${randomuser-api.period}", timeUnit = TimeUnit.SECONDS)
	public void synchronizeUsers() {
		LOGGER.info("polling {} users from  randomuser-api.", userAmount);
		List<UserResponse> userResponses = randomUserRESTClient.fetchUsers(userAmount);
		userResponses.forEach(ur -> {
			User createdUser = userService.createUser(ur);
			LOGGER.info("created {}", createdUser);
		});
	}
}
