package br.com.ronaldo.robot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MarsController {

	@RequestMapping(value="/rest/mars/{movimento}",method = RequestMethod.POST)
	public String welcome(@PathVariable("movimento") String movimento, Map<String, Object> model) {
		model.put("movimento", movimento);
		return "mars";
	}
}
