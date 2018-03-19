package net.minitt.hero.blog.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minitt.hero.blog.entity.User;
import net.minitt.hero.blog.service.UserService;
import net.minitt.hero.common.spring.BaseController;

@RestController
@RequestMapping("admin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(User searchUser,@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return renderPage(userService.findByPage(searchUser, pageable));
	}
}
