package br.com.mycoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyCoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCoinApplication.class, args);
	}

}
