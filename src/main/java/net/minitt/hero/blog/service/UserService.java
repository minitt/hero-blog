package net.minitt.hero.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.User;

public interface UserService {
	public User findUser(String username);
	public User findByUsername(String username);
	public Page<User> findByPage(User searchUser, Pageable pageable);
}
