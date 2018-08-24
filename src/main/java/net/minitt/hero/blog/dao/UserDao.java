package net.minitt.hero.blog.dao;

import net.minitt.hero.blog.entity.User;
import net.minitt.hero.core.base.BaseDao;

public interface UserDao extends BaseDao<User>{
	public User findByUsername(String username);
}
