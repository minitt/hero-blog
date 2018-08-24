package net.minitt.hero.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Search;
import net.minitt.hero.core.base.BaseElasticsearchDao;

public interface SearchDao extends BaseElasticsearchDao<Search>{
	Page<Search> findByTitleLikeOrContentLike(String name,String content,Pageable pageable);
	List<Search> findAll();
}
