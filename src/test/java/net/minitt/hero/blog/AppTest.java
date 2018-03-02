package net.minitt.hero.blog;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Unit test for simple App.
 */
public class AppTest{
	
	public static void main(String[] arg) {
		String x = new BCryptPasswordEncoder().encode("zt123123");
		System.out.println(x);
	}
}
