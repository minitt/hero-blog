package net.minitt.hero.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.core.dao.ResourceDao;
import net.minitt.hero.core.entity.Resource;
import net.minitt.hero.core.service.ResourceService;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}
}
