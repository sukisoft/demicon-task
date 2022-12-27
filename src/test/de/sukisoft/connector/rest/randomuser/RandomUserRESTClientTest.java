package de.sukisoft.connector.rest.randomuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@SpringBootTest
class RandomUserRESTClientTest {

	@MockBean
	private RestTemplate template;

	private RandomUserRESTClient client;

	@BeforeEach
	void setUp() {
		client = new RandomUserRESTClient(template);
	}

	@Test
	void shouldReturnUsersOn200StatusCode() {
		when(template.getForEntity(anyString(), eq(RandomUserResponse.class))).thenReturn(
				new ResponseEntity<>(RandomUserResponse.builder().results(List.of(UserResponse.builder().build())).build(), HttpStatus.OK));

		List<UserResponse> userResponses = client.fetchUsers(1);
		assertNotNull(userResponses);
		assertEquals(1, userResponses.size(), "should find 1 User");
	}

	@Test
	void shouldReturnEmpytListOnOtherCodesStatusCode() {
		when(template.getForEntity(anyString(), eq(RandomUserResponse.class))).thenReturn(
				new ResponseEntity<>(RandomUserResponse.builder().build(), HttpStatus.BAD_REQUEST));

		List<UserResponse> userResponses = client.fetchUsers(1);
		assertNotNull(userResponses);
		assertEquals(0, userResponses.size(), "should be empty");
	}
}