package net.minitt.hero.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.core.base.BaseController;
import net.minitt.hero.core.entity.Resource;
import net.minitt.hero.core.service.ResourceService;

@RestController
@RequestMapping("admin/resource")
public class ResourceController extends BaseController{
	
	@Autowired
	private ResourceService resourceService;
	
	public Map<String,Object> menuTree(){
		List<Resource> all = resourceService.findAll();
		return null;
	}
}
