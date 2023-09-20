package com.abi.swaggerapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
//java -jar wiremock-standalone-3.0.1.jar --port 8082 --verbose true --global-response-templating

	}

}
