package hello.Spring_Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 서버 접속시 templates 있는 home.html 파일을 띄워준다.
	@GetMapping("/")
	public String home() {
		return "home";
	}

}
