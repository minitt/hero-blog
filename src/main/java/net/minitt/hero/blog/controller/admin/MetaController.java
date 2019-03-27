package net.minitt.hero.blog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.blog.entity.Meta;
import net.minitt.hero.blog.service.MetaService;
import net.minitt.hero.core.base.BaseController;
import net.minitt.hero.core.base.ResultJson;

@RestController
@RequestMapping("admin/meta")
public class MetaController extends BaseController{

	@Autowired
	private MetaService metaService;
	
	@RequestMapping("list")
	public ResultJson<Page<Meta>> list(Meta searchMeta,@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return renderJson(metaService.findByPage(searchMeta, pageable));
	}
	
	@RequestMapping("search")
	public ResultJson<List<Meta>> search(Meta searchMeta){
		return renderJson(metaService.findAll(searchMeta));
	}
	
	@RequestMapping("create")
	public ResultJson<String> create(@RequestBody @Validated Meta meta){
		if(meta==null)
			throw new IllegalArgumentException("Parameter error!meta is null");
		metaService.save(meta);
		return renderSuceess();
	}
	
	@RequestMapping("update")
	public ResultJson<String> update(@RequestBody @Validated Meta meta){
		if(meta==null)
			throw new IllegalArgumentException("Parameter error!meta is null");
		metaService.save(meta);
		return renderSuceess();
	}
	
	@RequestMapping("del")
	public ResultJson<String> del(Integer id){
		if(id==null)
			throw new IllegalArgumentException("Parameter error!id is null");
		metaService.deleteById(id);
		return renderSuceess();
	}
	
	@RequestMapping("delBatch")
	public ResultJson<String> delBatch(@RequestParam(value = "idArr[]")Integer[] idArr){
		if(idArr==null)
			throw new IllegalArgumentException("Parameter error!idArr is null!");
		metaService.deleteByIds(idArr);
		return renderSuceess();
	}
}
