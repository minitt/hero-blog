package net.minitt.hero.core.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		Sort sort = new Sort(Direction.ASC, "sort");
		return resourceDao.findAll(sort);
	}

	@Override
	public List<Resource> findMenus() {
		return resourceDao.findAll((Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			query.where(cb.equal(root.get("type"), Resource.RESOURCE_MENU_TYPE));
			return query.getRestriction();
		}, new Sort(Direction.ASC, "sort"));
	}

	@Override
	public List<Resource> findAll(Integer userId) {
		return resourceDao.findAll((Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			query.where(cb.equal(root.join("roles",JoinType.LEFT).join("userSet",JoinType.LEFT).get("id"), userId));
			return query.getRestriction();
		});
	}
}
