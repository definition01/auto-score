package com.sennan;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.sennan.server.mapper")
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.sennan")
public class AutoScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoScoreApplication.class, args);
	}

}
