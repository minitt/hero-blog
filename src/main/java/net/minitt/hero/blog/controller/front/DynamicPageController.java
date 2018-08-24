package net.minitt.hero.blog.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.entity.Search;
import net.minitt.hero.blog.service.ArticleService;
import net.minitt.hero.blog.service.SearchService;
import net.minitt.hero.core.base.BaseFrontController;

@Controller
public class DynamicPageController extends BaseFrontController{
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/")
	public String index(@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,ModelMap model) {
		model.put("aPage",articleService.findByPage(pageable));
		return render("index");
	}
	
	@RequestMapping("/api/json/articlepage")
	@ResponseBody
	public Page<Article> articlePageJson(@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,ModelMap model){
		return articleService.findByPage(pageable);
	}
	
	@RequestMapping("/a/{id}")
	public String article(@PathVariable int id,ModelMap model) {
		model.put("article", articleService.findById(id).get());
		return render("article");
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String searchCity(@PageableDefault(value = 20, sort = { "createdTime" }, direction = Sort.Direction.DESC) Pageable pageable,String keyword) {
		Page<Search> page = searchService.search(pageable, keyword);
		for(Search s:page.getContent()) {
			System.out.println("----"+s.getTitle());
		}
		return "OK";
	}
}
