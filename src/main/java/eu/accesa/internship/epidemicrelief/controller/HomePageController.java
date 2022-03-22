package eu.accesa.internship.epidemicrelief.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

	@GetMapping("/")
	public String welcome() {
		return "homepage";
	}
}