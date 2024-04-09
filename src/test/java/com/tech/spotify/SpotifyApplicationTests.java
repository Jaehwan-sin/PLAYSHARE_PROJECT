package com.tech.spotify;

import com.tech.spotify.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SpotifyApplicationTests {

	@Autowired
	private SecurityConfig securityConfig;

	@MockBean
	private ClientRegistrationRepository clientRegistrationRepository;

	@Test
	void contextLoads() {
	}

}
