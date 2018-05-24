package net.minitt.hero.blog.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.minitt.hero.blog.service.ArticleService;
import net.minitt.hero.common.spring.BaseFrontController;

@Controller
public class DynamicPageController extends BaseFrontController{
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/")
	public String index(@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,ModelMap model) {
		model.put("aPage",articleService.findByPage(pageable));
		return render("index");
	}
	
	@RequestMapping("/a/{id}")
	public String article(@PathVariable int id,ModelMap model) {
		model.put("article", articleService.findById(id).get());
		return render("article");
	}
}
