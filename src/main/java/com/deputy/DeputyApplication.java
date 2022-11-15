package com.deputy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DeputyApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "main";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DeputyApplication.class, args);
	}

}
