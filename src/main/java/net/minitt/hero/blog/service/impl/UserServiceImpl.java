package net.minitt.hero.blog.service.impl;

import java.util.List;

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
	
	@Override
	public Page<User> findByPage(final User searchUser, Pageable pageable){
		return userDao.findAll(getSpec(searchUser), pageable);
	}
	
	private Specification<User> getSpec(final User user){
		return new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				if(!StringUtils.isEmpty(user.getScreenName())){
					expressions.add(cb.like(root.get("screenName"), "%"+user.getScreenName()+"%"));
				}
				return predicate;
			}
		};
	}

}
