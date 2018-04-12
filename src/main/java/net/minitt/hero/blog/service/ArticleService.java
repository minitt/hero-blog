package net.minitt.hero.blog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Article;

public interface ArticleService {
	public Page<Article> findByPage(Article searchArticle, Pageable pageable);
	public Article save(Article article);
	public Optional<Article> findById(Integer id);
	public void deleteById(Integer id);
	public void deleteByIds(Integer[] ids);
}
