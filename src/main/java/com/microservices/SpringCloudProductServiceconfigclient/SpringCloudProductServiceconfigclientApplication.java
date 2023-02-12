package com.microservices.SpringCloudProductServiceconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudProductServiceconfigclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProductServiceconfigclientApplication.class, args);
	}

}
