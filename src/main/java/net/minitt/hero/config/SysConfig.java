package net.minitt.hero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class SysConfig {
	/**
	 * 解决hibernate双向关联转JSON循环引用问题
	 * @return
	 */
	@Bean
	public ObjectMapper ObjectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		return mapper;
	}
}
