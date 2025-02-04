package com.backEndJavaSpring.Chatop_app;

import com.backEndJavaSpring.Chatop_app.Entity.User;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ChatopAppApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	MessageService messageService;
	@Autowired
	RentalService rentalService;

	public static void main(String[] args) {
		SpringApplication.run(ChatopAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//user();
		//readUser();
		//updateUser();
		//rental();
	}

	private void rental() {
	}

	private void updateUser() {
		User user = new User();
		user= userService.getUserById(1L);
		user.setName("Samira");
		user.setEmail("samira@gmail.com");
		userService.updateUser(user);
		readUser();
	}

	private void readUser() {
		User user = new User();
		user= userService.getUserById(1L);
		System.out.println("uuuuuuuuuuuuuuser"+user.getEmail());
	}

	private void user() {
		User user = new User();
		user.setName("John");
		user.setEmail("john@gmail.com");
		user.setPassword("123456");
		//LocalDateTime myObj = LocalDateTime.now(); // Create a date object
		//user.setCreated_at(myObj);
        userService.addUser(user);
	}
}
