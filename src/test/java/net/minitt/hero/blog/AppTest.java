package net.minitt.hero.blog;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.minitt.hero.App;
import net.minitt.hero.blog.dao.UserDao;
import net.minitt.hero.blog.entity.Meta;
import net.minitt.hero.blog.service.MetaService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class AppTest{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MetaService metaService;
	
	@Test
	public void findMetaByName() {
		Optional<Meta> metao = metaService.findOneByName("安装nginx");
		if(metao.isPresent()) {
			System.out.println(1);
		}
	}
	
	@Test
	public void insertUser() {
//		String x = new BCryptPasswordEncoder().encode("123456");
//		User u = new User();
//		u.setActivatedTime(new Date().getTime());
//		u.setCreatedTime(new Date().getTime());
//		u.setEmail("253542831@qq.com");
//		u.setPassword(x);
//		u.setScreenName("管理员");
//		u.setUsername("minitt");
//		userDao.save(u);
	}
	
	@Test
	public void searchArticle() {
	}
	
	@Test
	public void saveCity() {
	}
	
	@Test
	public void searchCity() {
	}
}
