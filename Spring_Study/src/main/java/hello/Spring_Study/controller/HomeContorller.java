package hello.Spring_Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContorller {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
}
