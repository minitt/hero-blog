package net.minitt.hero.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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

import net.minitt.hero.blog.dao.ArticleDao;
import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.entity.Meta;
import net.minitt.hero.blog.service.ArticleService;
import net.minitt.hero.blog.service.MetaService;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
	@Autowired
    private ArticleDao articleDao;
	
	@Autowired
	private MetaService metaService;

	@Override
	public Page<Article> findByPage(Article searchArticle, Pageable pageable) {
		return articleDao.findAll(getSpec(searchArticle), pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public Article save(Article article,Set<Integer> types) {
		if(article.getId()==null) {
			article.setCreatedTime(new Date().getTime());
		}else {
			article.setModifiedTime(new Date().getTime());
		}
		String categories = null;
		if(types!=null) {
			List<Meta> typeList = metaService.findAllByIds(types);
			categories = Meta.fetchNames(typeList);
			article.addTypes(typeList);
		}
		article.setModifiedTime(new Date().getTime());
		article.setCategories(categories);
		return articleDao.save(article);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Integer id) {
		articleDao.deleteById(id);
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
	
	private Specification<Article> getSpec(final Article article) {
		return new Specification<Article>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Article> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				if(!StringUtils.isEmpty(article.getTitle())){
					expressions.add(cb.like(root.get("screenName"), "%"+article.getTitle()+"%"));
				}
				return predicate;
			}
		};
	}

	@Override
	public Optional<Article> findById(Integer id) {
		return articleDao.findById(id);
	}

	@Override
	public Page<Article> findByPage(Pageable pageable) {
		return articleDao.findAll(pageable);
	}
}
