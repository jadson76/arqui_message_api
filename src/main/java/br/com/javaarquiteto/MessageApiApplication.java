package br.com.javaarquiteto;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MessageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApiApplication.class, args);
	}

}
