package net.minitt.hero.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest{
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void insertUser() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Hello World");
	}
}
