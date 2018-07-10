package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DesafioEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioEurekaApplication.class, args);
	}
}
