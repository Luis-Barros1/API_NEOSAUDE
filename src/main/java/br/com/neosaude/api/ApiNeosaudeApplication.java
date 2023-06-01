package br.com.neosaude.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiNeosaudeApplication {

	public static void main(String[] args) {
		System.out.println("Versão 0");
		SpringApplication.run(ApiNeosaudeApplication.class, args);
	}

}
