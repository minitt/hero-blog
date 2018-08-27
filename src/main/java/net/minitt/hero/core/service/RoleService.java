package net.minitt.hero.core.service;

import java.util.Optional;

import net.minitt.hero.core.entity.Role;

public interface RoleService {
	public Role save(Role role);
	public Optional<Role> findById(Integer id);
}
