package net.minitt.hero.common.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Integer>,JpaSpecificationExecutor<T>{
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public List<T> findAll();
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public Page<T> findAll(Specification<T> spec, Pageable page);
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public List<T> findAll(Specification<T> spec, Sort sort);
//	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
//	public T findOne(Serializable id);
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public Optional<T> findById(Integer id);
	public List<T> findAllById(Iterable<Integer> ids);
}