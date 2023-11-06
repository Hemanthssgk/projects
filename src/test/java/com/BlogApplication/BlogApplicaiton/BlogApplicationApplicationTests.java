package com.BlogApplication.BlogApplicaiton;

import com.BlogApplication.BlogApplicaiton.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Test
	void contextLoads() {
		System.out.println(userRepository.getClass().getName());
		System.out.println(userRepository.getClass().getPackage());
	}

}
