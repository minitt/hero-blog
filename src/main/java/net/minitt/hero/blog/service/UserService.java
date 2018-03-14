package net.minitt.hero.blog.service;

import net.minitt.hero.blog.entity.User;

public interface UserService {
	public User findUser(String username);
	public User findByUsername(String username);
}
