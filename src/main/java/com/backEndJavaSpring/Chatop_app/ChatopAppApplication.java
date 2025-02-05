package com.backEndJavaSpring.Chatop_app;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		//message();
	}

	private void message() {
		MessageDto message = new MessageDto();
		message.setMessage("Hello I'm interested in ...");
		UserDto user = new UserDto();
		user= userService.getUserById(2L);
		message.setUser(user);
		message.setRental(rentalService.getRentalById(1L));
		messageService.addMessage(message);

	}

	private void rental() {
		RentalDto rental = new RentalDto();
		rental.setName("T1 Boulogne");
		UserDto user = new UserDto();
		user= userService.getUserById(1L);
		rental.setOwner(user);
		rental.setPrice(750f);
		rental.setSurface(24f);
		rental.setDescription("T1 Boulogne");
		rentalService.addRental(rental);
	}


	private void updateUser() {
		UserDto user = new UserDto();
		user= userService.getUserById(1L);
		user.setName("Samira");
		user.setEmail("samira@gmail.com");
		userService.updateUser(user);
		readUser();
	}

	private void readUser() {
		UserDto user = new UserDto();
		user= userService.getUserById(1L);
		System.out.println("uuuuuuuuuuuuuuser"+user.getEmail());
	}

	private void user() {
		UserDto user = new UserDto();
		user.setName("John");
		user.setEmail("john@gmail.com");
		user.setPassword("123456");
        userService.addUser(user);
	}
}
