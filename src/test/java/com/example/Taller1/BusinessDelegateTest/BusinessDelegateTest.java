package com.example.Taller1.BusinessDelegateTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;

@SpringBootTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(SpringExtension.class)
public class BusinessDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private BusinessDelegate delegate;
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- BUSINESS DELEGATE TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Test methods for sales person")
	class Salesperson{
		@BeforeEach
		void setUp() {
			
		}
		
	}

}
