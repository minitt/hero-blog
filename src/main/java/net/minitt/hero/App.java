package net.minitt.hero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //相当于@Configuration+@EnableAutoConfiguration+@ComponentScan 见源码
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = { "net.minitt.hero" })
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
