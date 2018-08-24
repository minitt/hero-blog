package net.minitt.hero.blog.controller.admin;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.service.ArticleService;
import net.minitt.hero.core.base.BaseController;

@RestController
@RequestMapping("admin/article")
public class ArticleController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("list")
	public Map<String,Object> list(Article searchArticle,@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return renderJson(articleService.findByPage(searchArticle, pageable));
	}
	
	@RequestMapping("fetch")
	public Map<String,Object> fetch(Integer id){
		if(id==null)
			throw new IllegalArgumentException("Parameter error!id is null");
		return renderJson(articleService.findById(id));
	}
	
	@RequestMapping("create")
	public Map<String,Object> create(@RequestBody @Validated Article article,@RequestParam(value = "typeids[]")Set<Integer> typeArr,@RequestParam(value = "tagnames[]")Set<String> tagnameArr){
		if(article==null)
			throw new IllegalArgumentException("Parameter error!article is null");
		article.setAuthor(getCurrUser().getUser());
		articleService.save(article,typeArr,tagnameArr);
		return renderSuceess();
	}
	
	@RequestMapping("draft")
	public Map<String,Object> draft(@RequestBody @Validated Article article,@RequestParam(value = "typeids[]")Set<Integer> typeArr,@RequestParam(value = "tagnames[]")Set<String> tagnameArr){
		if(article==null)
			throw new IllegalArgumentException("Parameter error!article is null");
		article.setAuthor(getCurrUser().getUser());
		articleService.draft(article,typeArr,tagnameArr);
		return renderSuceess();
	}
	
	@RequestMapping("update")
	public Map<String,Object> update(@RequestBody @Validated Article article,@RequestParam(value = "typeids[]")Set<Integer> typeArr,@RequestParam(value = "tagnames[]")Set<String> tagnameArr){
		if(article==null)
			throw new IllegalArgumentException("Parameter error!Article is null");
		article.setAuthor(getCurrUser().getUser());
		articleService.save(article,typeArr,tagnameArr);
		return renderSuceess();
	}
	
	@RequestMapping("del")
	public Map<String,Object> del(Integer id){
		if(id==null)
			throw new IllegalArgumentException("Parameter error!id is null");
		articleService.deleteById(id);
		return renderSuceess();
	}
	
	@RequestMapping("delBatch")
	public Map<String,Object> delBatch(@RequestParam(value = "idArr[]")Integer[] idArr){
		if(idArr==null)
			throw new IllegalArgumentException("Parameter error!idArr is null!");
		articleService.deleteByIds(idArr);
		return renderSuceess();
	}
}
