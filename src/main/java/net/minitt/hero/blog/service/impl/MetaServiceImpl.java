package net.minitt.hero.blog.service.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import net.minitt.hero.blog.dao.MetaDao;
import net.minitt.hero.blog.entity.Meta;
import net.minitt.hero.blog.service.MetaService;

@Service
@Transactional(readOnly = true)
public class MetaServiceImpl implements MetaService{
	
	@Autowired
	private MetaDao metaDao;

	@Override
	public Page<Meta> findByPage(final Meta searchMeta, Pageable pageable) {
		return metaDao.findAll(getSpec(searchMeta), pageable);
	}
	
	private Specification<Meta> getSpec(final Meta meta) {
		return new Specification<Meta>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Meta> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				if(!StringUtils.isEmpty(meta.getName())){
					expressions.add(cb.like(root.get("name"), "%"+meta.getName()+"%"));
				}
				expressions.add(cb.equal(root.get("type"), Meta.TYPE_CATEGORY));
				return predicate;
			}
		};
	}

	@Override
	@Transactional(readOnly = false)
	public Meta save(Meta meta) {
		return metaDao.save(meta);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Integer id) {
		metaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteByIds(Integer[] ids) {
		if(ids!=null) {
			for(Integer id:ids) {
				deleteById(id);
			}
		}
	}

	@Override
	public List<Meta> findAll(Meta searchMeta) {
		return metaDao.findAll(getSpec(searchMeta));
	}

	@Override
	public List<Meta> findAllByIds(Set<Integer> ids) {
		return metaDao.findAllById(ids);
	}
}
