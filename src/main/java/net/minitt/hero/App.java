package net.minitt.hero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication 相当于@Configuration+@EnableAutoConfiguration+@ComponentScan 见源码
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "net.minitt.hero" })
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
