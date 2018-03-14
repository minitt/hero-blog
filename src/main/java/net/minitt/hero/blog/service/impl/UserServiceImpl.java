package net.minitt.hero.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.blog.dao.UserDao;
import net.minitt.hero.blog.entity.User;
import net.minitt.hero.blog.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	@Autowired
    private UserDao userDao;

	@Override
	public User findUser(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
