package com.tanthanh.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(Sink.class)
public class NotificationserviceApplication {

	@StreamListener(Sink.INPUT) 
	public void consumeMessage(String message) {
		
		System.out.println("Message" + message);
	}
	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

}
