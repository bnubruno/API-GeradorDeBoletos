package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DesafioConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioConfigApplication.class, args);
	}
}
