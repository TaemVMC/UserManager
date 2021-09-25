package com.verifymycoin.UserManager;

import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
