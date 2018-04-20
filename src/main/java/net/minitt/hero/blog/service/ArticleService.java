package net.minitt.hero.blog.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Article;

public interface ArticleService {
	public Page<Article> findByPage(Article searchArticle, Pageable pageable);
	public Page<Article> findByPage(Pageable pageable);
	public Article save(Article article,Set<Integer> typeArr);
	public Optional<Article> findById(Integer id);
	public void deleteById(Integer id);
	public void deleteByIds(Integer[] ids);
}
