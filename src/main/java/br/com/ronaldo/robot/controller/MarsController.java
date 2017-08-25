package br.com.ronaldo.robot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ronaldo.robot.model.Robot;

@RestController
public class MarsController {
	
	
	Robot robo;

	@RequestMapping(value="/rest/mars/{movimento}",method = RequestMethod.POST)
	public Robot moveRobot(@PathVariable("movimento") String movimento) throws Exception {
		robo = new Robot(); 
		robo.processEntry(movimento);
		 return robo;
	}
	
	
	@GetMapping
	public ModelAndView telaWelcome() {
		ModelAndView modelAndView = new ModelAndView("welcome");
		return modelAndView;
	}
	

	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
