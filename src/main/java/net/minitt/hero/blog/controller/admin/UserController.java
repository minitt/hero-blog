package net.minitt.hero.blog.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.blog.entity.User;
import net.minitt.hero.blog.service.UserService;
import net.minitt.hero.core.base.BaseController;

@RestController
@RequestMapping("admin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	public Map<String,Object> list(User searchUser,@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return renderJson(userService.findByPage(searchUser, pageable));
	}
	
	@RequestMapping("create")
	public Map<String,Object> create(@RequestBody @Validated User user,String password){
		if(user==null)
			throw new IllegalArgumentException("Parameter error!user is null");
		user.setPassword(password);
		userService.save(user);
		return renderSuceess();
	}
	
	@RequestMapping("update")
	public Map<String,Object> update(@RequestBody @Validated User user,String password){
		user.setPassword(password);
		userService.save(user);
		return renderSuceess();
	}
	
	@RequestMapping("del")
	public Map<String,Object> del(Integer id){
		if(id==null)
			throw new IllegalArgumentException("Parameter error!id is null");
		userService.deleteById(id);
		return renderSuceess();
	}
	
	@RequestMapping("delBatch")
	public Map<String,Object> delBatch(@RequestParam(value = "idArr[]")Integer[] idArr){
		if(idArr==null)
			throw new IllegalArgumentException("Parameter error!idArr is null!");
		userService.deleteByIds(idArr);
		return renderSuceess();
	}
}
