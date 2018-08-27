package net.minitt.hero.core.dao;

import net.minitt.hero.core.base.BaseDao;
import net.minitt.hero.core.entity.User;

public interface UserDao extends BaseDao<User>{
	public User findByUsername(String username);
}
