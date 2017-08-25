package br.com.ronaldo.robot;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest(value = MarsController.class, secure = false)
public class MarsControllerTest {

}
 