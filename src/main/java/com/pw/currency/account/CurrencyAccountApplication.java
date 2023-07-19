package com.pw.currency.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyAccountApplication.class, args);
	}

}
