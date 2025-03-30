package com.example.UseRestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UseRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseRestTemplateApplication.class, args);
	}

//    @Bean
//    RestTemplate restTemplate() {
////		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
////		RestTemplate restTemplate = new RestTemplate(factory);
//		return new RestTemplate();
//	}
}
