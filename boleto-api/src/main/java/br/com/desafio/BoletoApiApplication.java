package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BoletoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletoApiApplication.class, args);
	}
}
