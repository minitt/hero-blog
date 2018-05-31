package net.minitt.hero.blog;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import net.minitt.hero.freemark.method.HtmlToTextMethod;
import net.minitt.hero.freemark.method.MdToHtmlMethod;
import net.minitt.hero.freemark.method.UnixTimestampMethod;

//@SpringBootApplication 相当于@Configuration+@EnableAutoConfiguration+@ComponentScan 见源码
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "net.minitt.hero" })
public class App {
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		return new CorsFilter(source);
	}
	
	@Bean
	public ObjectMapper ObjectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		return mapper;
	}
	
	@Bean
	public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
		return new CommandLineRunner() {
		    @Override
		    public void run(String... strings) throws Exception {
		        //添加自定义解析器
				Map<String,Object> map = resolver.getAttributesMap();
				map.put("htmlToText", new HtmlToTextMethod());
				map.put("mdToHtml", new MdToHtmlMethod());
				map.put("unixTimestamp", new UnixTimestampMethod());
		    }
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
