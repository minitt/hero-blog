package net.minitt.hero.core.controller;

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

import net.minitt.hero.core.base.BaseController;
import net.minitt.hero.core.base.ResultJson;
import net.minitt.hero.core.entity.User;
import net.minitt.hero.core.service.UserService;

@RestController
@RequestMapping("admin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	public ResultJson<Page<User>> list(User searchUser,@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return renderJson(userService.findByPage(searchUser, pageable));
	}
	
	@RequestMapping("create")
	public ResultJson<String> create(@RequestBody @Validated User user,String password){
		if(user==null)
			throw new IllegalArgumentException("Parameter error!user is null");
		user.setPassword(password);
		userService.save(user);
		return renderSuceess();
	}
	
	@RequestMapping("update")
	public ResultJson<String> update(@RequestBody @Validated User user,String password){
		user.setPassword(password);
		userService.save(user);
		return renderSuceess();
	}
	
	@RequestMapping("del")
	public ResultJson<String> del(Integer id){
		if(id==null)
			throw new IllegalArgumentException("Parameter error!id is null");
		userService.deleteById(id);
		return renderSuceess();
	}
	
	@RequestMapping("delBatch")
	public ResultJson<String> delBatch(@RequestParam(value = "idArr[]")Integer[] idArr){
		if(idArr==null)
			throw new IllegalArgumentException("Parameter error!idArr is null!");
		userService.deleteByIds(idArr);
		return renderSuceess();
	}
}
