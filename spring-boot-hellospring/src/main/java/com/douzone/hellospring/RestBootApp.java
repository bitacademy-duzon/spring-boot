package com.douzone.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestBootApp {
	
	@RestController
	public class MyController {
		
		@Autowired
		MyService myService;
		
		@GetMapping("/hello")
		public String hello() {
			return myService.hello();
		}
	}
	
	@Component
	public class MyService {
		public String hello() {
			return "Hello World";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
