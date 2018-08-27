package net.minitt.hero.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.core.base.BaseController;

@RestController
@RequestMapping("admin")
public class IndexController extends BaseController{
	
	@RequestMapping("/")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("info")
	public Map<String,Object> info(String token){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("roles", new String[] {"1"});
		data.put("name", "minitt");
		data.put("avatar", "5555");
		return renderJson(data);
	}
}
