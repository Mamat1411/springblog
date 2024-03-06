package com.springexercise.springblog;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.javafaker.Faker;
import com.springexercise.springblog.users.entities.User;
import com.springexercise.springblog.users.repositories.UserRepository;

@SpringBootApplication
public class SpringblogApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringblogApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args->{
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			Faker faker = new Faker(new Locale("en-US"));
			User user = new User("pembekalanJava", "pembekalan@gmail.com", bcrypt.encode("123456"));
			userRepository.save(user);

			int users = 10;
			for (int i = 0; i < users; i++) {
				User userSeed = new User(faker.name().fullName(), faker.internet().safeEmailAddress(), bcrypt.encode("12346"));
				userRepository.save(userSeed);
			}
		};
	}

}
