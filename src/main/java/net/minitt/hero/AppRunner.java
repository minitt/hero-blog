package net.minitt.hero;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.core.dao.ResourceDao;
import net.minitt.hero.core.dao.RoleDao;
import net.minitt.hero.core.dao.UserDao;
import net.minitt.hero.core.entity.Resource;
import net.minitt.hero.core.entity.Role;
import net.minitt.hero.core.entity.User;

@Component
public class AppRunner implements ApplicationRunner {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ResourceDao resourceDao;

	@Override
	@Transactional(readOnly = false)
	public void run(ApplicationArguments args) throws Exception {
		if(roleDao.findAll().size()<=0) {
			Role role = new Role();
			role.setAllowDel(false);
			role.setName("超级管理员");
			role.setDesc("超级管理员");
			
			Resource r = new Resource();
			r.setName("系统管理");
			r.setPath("/sys");
			r.setIcon("example");
			r.setSort(1000);
			r.setHidden(false);
			r.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r);
			role.addResource(r);
			
			Resource r1 = new Resource();
			r1.setName("系统配置");
			r1.setPath("/sys/sysconfig");
			r1.setParent(r);
			r1.setIcon("example");
			r1.setSort(500);
			r1.setHidden(false);
			r1.setAuth("sys.sysconfig:*");
			r1.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r1);
			role.addResource(r1);
			
			Resource r2 = new Resource();
			r2.setName("用户管理");
			r2.setPath("/sys/user");
			r2.setParent(r);
			r2.setIcon("example");
			r2.setSort(1);
			r2.setHidden(false);
			r2.setAuth("sys.user:*");
			r2.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r2);
			role.addResource(r2);
			
			Resource r3 = new Resource();
			r3.setName("角色管理");
			r3.setPath("/sys/role");
			r3.setParent(r);
			r3.setIcon("example");
			r3.setSort(2);
			r3.setHidden(false);
			r3.setAuth("sys.role:*");
			r3.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r3);
			role.addResource(r3);
			
			Resource r4 = new Resource();
			r4.setName("资源管理");
			r4.setPath("/sys/resource");
			r4.setParent(r);
			r4.setIcon("example");
			r4.setSort(3);
			r4.setHidden(false);
			r4.setAuth("sys.resource:*");
			r4.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r4);
			role.addResource(r4);
			
			Resource r5 = new Resource();
			r5.setName("发布文章");
			r5.setPath("/article/form");
			r5.setIcon("example");
			r5.setSort(1);
			r5.setAuth("article.form:*");
			r5.setHidden(true);
			r5.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r5);
			role.addResource(r5);
			
			Resource r6 = new Resource();
			r6.setName("文章管理");
			r6.setPath("/article");
			r6.setIcon("example");
			r6.setSort(2);
			r6.setAuth("article:*");
			r6.setHidden(false);
			r6.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r6);
			role.addResource(r6);
			
			Resource r7 = new Resource();
			r7.setName("分类/标签");
			r7.setPath("/meta");
			r7.setIcon("example");
			r7.setSort(3);
			r7.setAuth("meta:*");
			r7.setHidden(false);
			r7.setType(Resource.RESOURCE_MENU_TYPE);
			resourceDao.save(r7);
			role.addResource(r7);
			
			Resource r8 = new Resource();
			r8.setName("新增用户");
			r8.setPath("/sys/user/addUserView");
			r8.setIcon("example");
			r8.setSort(2);
			r8.setAuth("sys.user:addUser");
			r8.setHidden(false);
			r8.setType(Resource.RESOURCE_FUN_TYPE);
			resourceDao.save(r8);
			role.addResource(r8);
			
			roleDao.save(role);
			
			String x = new BCryptPasswordEncoder().encode("123456");
			User u = new User();
			u.setActivatedTime(new Date().getTime());
			u.setCreatedTime(new Date().getTime());
			u.setEmail("253542831@qq.com");
			u.setTelNo("15925128606");
			u.setIsDel(false);
			u.setIsLocked(false);
			u.setPassword(x);
			u.setScreenName("管理员");
			u.setUsername("minitt");
			u.setRole(role);
			userDao.save(u);
		}
	}
}
