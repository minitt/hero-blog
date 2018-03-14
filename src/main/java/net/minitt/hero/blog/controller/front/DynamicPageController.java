package net.minitt.hero.blog.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DynamicPageController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
