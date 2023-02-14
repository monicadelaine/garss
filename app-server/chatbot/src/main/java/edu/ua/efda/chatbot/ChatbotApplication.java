package edu.ua.efda.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@ImportResource("classpath:app-config.xml")
public class ChatbotApplication {
 

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

}