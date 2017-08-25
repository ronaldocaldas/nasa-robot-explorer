package br.com.ronaldo.robot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.RequestDispatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.ronaldo.robot.controller.MarsController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MarsController.class, secure = false)
public class MarsControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testRobot() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());

	}

	@Test
	public void error400BadRequestInvalidChar() throws Exception {
		this.mockMvc.perform(post("/rest/mars/AAAAA").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400).requestAttr(
				RequestDispatcher.ERROR_MESSAGE, "The 'movimento' parameter must not be diferent of 'M', 'L' or 'R'"));
	}
	
	@Test
	public void error400BadRequestFellOut() throws Exception {
		this.mockMvc.perform(post("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400).requestAttr(
				RequestDispatcher.ERROR_MESSAGE, "The robot fell out dude! Build another one..."));
	}

	@Test
	public void testPosiction1() throws Exception {
		mockMvc.perform(post("/rest/mars/MMRMMRMM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(2, 0, S)"));

	}
}
