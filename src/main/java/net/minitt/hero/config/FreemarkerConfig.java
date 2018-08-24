package net.minitt.hero.config;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import net.minitt.hero.core.freemark.method.HtmlToTextMethod;
import net.minitt.hero.core.freemark.method.MdToHtmlMethod;
import net.minitt.hero.core.freemark.method.UnixTimestampMethod;

@Configuration
public class FreemarkerConfig {
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
}
