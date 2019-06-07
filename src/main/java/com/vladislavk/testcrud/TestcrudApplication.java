package com.vladislavk.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcrudApplication.class, args);
		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("123"));*/
	}
}
