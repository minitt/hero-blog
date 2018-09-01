package net.minitt.hero.blog.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.core.base.BaseController;
import net.minitt.hero.core.entity.Resource;
import net.minitt.hero.core.service.ResourceService;

@RestController
@RequestMapping("admin")
public class IndexController extends BaseController{
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("info")
	public Map<String,Object> info(String token){
		List<Resource> all = resourceService.findMenus();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("roles", new String[] {"admin"});
		data.put("resources", buildMenuTree(all));
		data.put("name", getCurrUser().getUser().getScreenName());
		data.put("avatar", "5555");
		return renderJson(data);
	}
	
	private List<Map<String,Object>> buildMenuTree(List<Resource> resList){
		List<Map<String,Object>> menuTree = new ArrayList<Map<String,Object>>();
		if(resList!=null) {
			for(Resource res:resList) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("id", res.getId());
				m.put("path", res.getPath());
				m.put("name", res.getPath().replaceAll("/", "_"));
				m.put("isleaf", isLeaf(res,resList));
				m.put("title", res.getName());
				m.put("icon", res.getIcon());
				m.put("pid", res.getParent()==null?null:res.getParent().getId());
				m.put("hidden", res.getHidden());
				menuTree.add(m);
			}
		}
		return menuTree;
	}
	
	private boolean isLeaf(Resource res,List<Resource> resList) {
		boolean isLeaf = true;
		for(Resource resource:resList) {
			if(resource.getParent()!=null&&resource.getParent().getId() == res.getId()) {
				isLeaf = false;
			}
		}
		return isLeaf;
	}
}
