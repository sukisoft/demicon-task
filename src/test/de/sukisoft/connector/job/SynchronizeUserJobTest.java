package de.sukisoft.connector.job;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.sukisoft.connector.db.UserService;
import de.sukisoft.connector.rest.randomuser.RandomUserRESTClient;
import de.sukisoft.connector.rest.randomuser.UserResponse;

@ExtendWith(MockitoExtension.class)
class SynchronizeUserJobTest {

	@Mock
	private RandomUserRESTClient randomUserRESTClient;

	@Mock
	private UserService userService;

	private SynchronizeUserJob synchronizeUserJob = new SynchronizeUserJob(randomUserRESTClient, 2, userService);

	@BeforeEach
	void setUp() {
		synchronizeUserJob = new SynchronizeUserJob(randomUserRESTClient, 2, userService);
	}

	@Test
	void shouldSaveUserWhenUserResponseIsFetched() {
		when(randomUserRESTClient.fetchUsers(2)).thenReturn(List.of(UserResponse.builder().build()));

		synchronizeUserJob.synchronizeUsers();

		verify(randomUserRESTClient, times(1)).fetchUsers(2);
		verify(userService, times(1)).createUser(isA(UserResponse.class));
	}

	@Test
	void shouldNotCallServiceWhenNoUserResponseIsFetched() {
		when(randomUserRESTClient.fetchUsers(2)).thenReturn(List.of());

		synchronizeUserJob.synchronizeUsers();

		verify(randomUserRESTClient, times(1)).fetchUsers(2);
		verify(userService, never()).createUser(isA(UserResponse.class));
	}
}