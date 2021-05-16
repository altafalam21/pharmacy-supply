package com.cognizant.authorization;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authorization.repository.MyUserRepository;
import com.cognizant.authorization.service.CustomerDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationServiceApplicationTests {

	@Autowired
	private CustomerDetailsService custDetailsService;
	
	@Mock
	private MyUserRepository userRepo;
	
	
	@Test
	void testloadUserByUsername() throws UsernameNotFoundException  {
		
		UserDetails  user = custDetailsService.loadUserByUsername("root");
		assertNotNull(user);
	}

}