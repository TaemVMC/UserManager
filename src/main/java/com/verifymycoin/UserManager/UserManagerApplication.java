package com.verifymycoin.UserManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRedisHttpSession
public class UserManagerApplication implements CommandLineRunner {

//	@Value("${foo}")
//	private String configExample;
//R
//	@RequestMapping("/")
//	public String home() {
//		return "Hello " + configExample;
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("redis session test ");

	}
}
