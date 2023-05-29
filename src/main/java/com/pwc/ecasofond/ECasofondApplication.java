package com.pwc.ecasofond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ECasofondApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECasofondApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Index Page <br /> <a href=\"/hello\">Hello</a>";
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello World! <br /> <a href=\"/\">Back</a>";
	}
}
