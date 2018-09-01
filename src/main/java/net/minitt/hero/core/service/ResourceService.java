package net.minitt.hero.core.service;

import java.util.List;

import net.minitt.hero.core.entity.Resource;

public interface ResourceService {
	public List<Resource> findAll();
	public List<Resource> findMenus();
	/**
	 * 获取用户下所有资源权限
	 * @param userId 用户ID
	 * @return 资源List
	 */
	public List<Resource> findAll(Integer userId);
}
