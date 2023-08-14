package study.basic.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}



}
