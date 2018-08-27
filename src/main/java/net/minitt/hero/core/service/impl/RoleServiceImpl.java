package net.minitt.hero.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.core.dao.RoleDao;
import net.minitt.hero.core.entity.Role;
import net.minitt.hero.core.service.RoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return roleDao.findById(id);
	}

}
