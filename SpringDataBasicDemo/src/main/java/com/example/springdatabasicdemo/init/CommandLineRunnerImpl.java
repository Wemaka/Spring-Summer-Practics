package com.example.springdatabasicdemo.init;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.ParcelDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.services.AddressService;
import com.example.springdatabasicdemo.services.ParcelService;
import com.example.springdatabasicdemo.services.StatusHistoryService;
import com.example.springdatabasicdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ParcelService parcelService;

	@Override
	public void run(String... args) throws Exception {
		seedData();
	}

	private void seedData() throws IOException {
		//Добавление в БД записей
		// Создаем адреса
		AddressDto senderAddress = new AddressDto(0, "5th Avenue", "New York", "10001", "USA");
		AddressDto recipientAddress = new AddressDto(0, "Sunset Blvd", "Los Angeles", "90001", "USA");
		AddressDto savedSenderAddress = addressService.createAddress(senderAddress);
		AddressDto savedRecipientAddress = addressService.createAddress(recipientAddress);

		// Создаем тестовых пользователей
		UserDto sender = new UserDto(0, "John Doe", "john@example.com", "+1234567890");
		sender.setAddress(savedSenderAddress);
		UserDto receiver = new UserDto(0, "Nikita", "nitkita@mail.ru", "+89223338890");


		UserDto savedSender = userService.register(sender);
		UserDto savedReceiver = userService.register(receiver);

		// Создаем посылку
		ParcelDto parcel = new ParcelDto(
				0,
				"AB123",
				savedSender,
				savedReceiver,
				addressService.createAddress(
						new AddressDto(0, "Kalinina", "Pscov", "998800", "Russia")
				)
		);
		ParcelDto savedParcel = parcelService.createParcel(parcel);

		addressService.getAllAddress().forEach(System.out::println);
		userService.getAllUsers().forEach(System.out::println);
		parcelService.getAllParcel().forEach(System.out::println);
	}
}
