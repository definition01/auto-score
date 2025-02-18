package com.sennan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.sennan")
public class AutoScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoScoreApplication.class, args);
	}

}
