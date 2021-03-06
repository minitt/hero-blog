package net.minitt.hero.core.base;

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
import org.springframework.lang.Nullable;

@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Integer>,JpaSpecificationExecutor<T>{
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public List<T> findAll();
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public Page<T> findAll(@Nullable Specification<T> spec, Pageable page);
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public List<T> findAll(@Nullable Specification<T> spec, Sort sort);
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public Optional<T> findOne(@Nullable Specification<T> spec);
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")})
	public List<T> findAllById(Iterable<Integer> ids);
}