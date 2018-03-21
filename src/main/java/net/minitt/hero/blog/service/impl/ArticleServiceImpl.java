package net.minitt.hero.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.blog.dao.ArticleDao;
import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.service.ArticleService;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
	@Autowired
    private ArticleDao articleDao;

	@Override
	public Page<Article> findByPage(Article searchArticle, Pageable pageable) {
		return null;
	}

	@Override
	public Article save(Article article) {
		return null;
	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public void deleteByIds(Integer[] ids) {

	}

}
