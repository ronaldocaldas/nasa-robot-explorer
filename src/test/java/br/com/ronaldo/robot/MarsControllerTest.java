package br.com.ronaldo.robot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	public void error400BadRequest() throws Exception {
		this.mockMvc.perform(post("/rest/mars/AAAAA").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400).requestAttr(
				RequestDispatcher.ERROR_MESSAGE, "The 'movimento' parameter must not be diferent of 'M', 'L' or 'R'"));
	}

}
