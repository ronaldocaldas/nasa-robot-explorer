package br.com.ronaldo.robot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MarsController {

	@RequestMapping(value="/rest/mars/{movimento}",method = RequestMethod.POST)
	public String welcome(@PathVariable("movimento") String movimento, Map<String, Object> model) {
		model.put("movimento", movimento);
		return "mars";
	}
	
	
	@GetMapping
	public ModelAndView movimentaRobo() {
		ModelAndView modelAndView = new ModelAndView("welcome");
		return modelAndView;
	}
}
