package de.sukisoft.connector.rest.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import de.sukisoft.connector.db.CountryService;
import lombok.SneakyThrows;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CountryRESTControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private CountryService countryService;

	@SneakyThrows
	@Test
	void shouldFindExistingTestCountry() {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/countries")).andExpect(status().isOk()).andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponseBody).contains("country-1");

		verify(countryService, times(1)).findAllCountries();
	}

	@SneakyThrows
	@Test
	void shouldReturnCountriesResponse() {
		MvcResult mvcResult = this.mockMvc.perform(get("/api")).andExpect(status().isOk()).andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponseBody).contains("country-1").contains("user-1-first").contains("user-1-last").contains("male").contains("@");

		verify(countryService, times(1)).getCountriesResponse();
	}

}