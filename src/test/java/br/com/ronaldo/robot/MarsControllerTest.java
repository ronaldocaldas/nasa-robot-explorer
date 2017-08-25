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
	
	@Test
	public void testPosiction2() throws Exception {
		mockMvc.perform(post("/rest/mars/MRMMMMLM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(0, 2, N)"));

	}
	
	@Test
	public void testPosiction3() throws Exception {
		mockMvc.perform(post("/rest/mars/MMMMRMMRM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(2, 3, S)"));

	}
	
	@Test
	public void testPosiction4() throws Exception {
		//OBS: aqui eu levei em consideração que quando o robô está virado para o Sul e vira para a esquerda ele vira para o OESTE
		//ou seja, muda com relação ao seu  próprio eixo, e não com relação ao observador.
		//Isso não estava especificado no teste, portanto deduzi que esse seria o comportamento esperado.
		
		mockMvc.perform(post("/rest/mars/MMMMRMMRMLMM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(4, 3, L)"));

	}
	
	@Test
	public void testPosiction5() throws Exception {
		mockMvc.perform(post("/rest/mars/MRMMMMLMLM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(3, 2, W)"));

	}
	
	@Test
	public void testPosiction6() throws Exception {
		mockMvc.perform(post("/rest/mars/MRMLMLM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(0, 2, W)"));

	}
	
	@Test
	public void testPosiction7() throws Exception {
		mockMvc.perform(post("/rest/mars/MRMMM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(3, 1, L)"));

	}
	
	
	@Test
	public void testPosiction8() throws Exception {
		mockMvc.perform(post("/rest/mars/MRMMMLMRM")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.actualPosition").value("(4, 2, L)"));

	}
	
}
