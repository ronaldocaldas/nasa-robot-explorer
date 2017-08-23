package br.com.ronaldo.robot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MarsController {

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "Olá mundo");
		return "welcome";
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("mars");
		return modelAndView;
	}
}
