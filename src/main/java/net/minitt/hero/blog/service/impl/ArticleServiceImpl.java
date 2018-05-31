package net.minitt.hero.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.Session;
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
	@PersistenceContext 
    private EntityManager entityManager;
	
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
	public Article save(Article article,Set<Integer> types,Set<String> tagnameArr,String status) {
		if(article.getId()==null) {
			article.setCreatedTime(new Date().getTime());
		}else {
			article.setModifiedTime(new Date().getTime());
		}
		String categories = null;
		String tags = null;
		if(types!=null) {
			List<Meta> typeList = metaService.findAllByIds(types);
			categories = Meta.fetchNames(typeList);
			article.addTypes(typeList);
		}
		if(tagnameArr!=null) {
			List<Meta> tagList = new ArrayList<Meta>();
			for(String tagname:tagnameArr) {
				Meta tag = new Meta();
				tag.setName(tagname);
				tag.setType(Meta.TYPE_TAG);
				tag.setOrderby(0);
				article.addTag(metaService.save(tag));
				tagList.add(tag);
			}
			tags = Meta.fetchNames(tagList);
		}
		article.setFmtType(Article.FMT_TYPE_MD);//暂时只支持markdown
		article.setModifiedTime(new Date().getTime());
		article.setCategories(categories);
		article.setTags(tags);
		article.setHits(0);
		article.setStatus(status);
		article.setAllowComment(false);
		return articleDao.save(article);
	}

	@Override
	@Transactional(readOnly = false)
	public Article save(Article article,Set<Integer> types,Set<String> tagnameArr) {
		return save(article,types,tagnameArr,Article.STATUS_PUBLISH);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Article draft(Article article,Set<Integer> types,Set<String> tagnameArr) {
		return save(article,types,tagnameArr,Article.STATUS_DRAFT);
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
					expressions.add(cb.like(root.get("title"), "%"+article.getTitle()+"%"));
				}
				return predicate;
			}
		};
	}

	@Override
	public Optional<Article> findById(Integer id) {
		return articleDao.findOne(new Specification<Article>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				root.fetch("typeSet",JoinType.LEFT);
				root.fetch("tagSet",JoinType.LEFT);
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				expressions.add(cb.equal(root.get("id"), id));
				return predicate;
			}
			
		});
	}

	@Override
	public Page<Article> findByPage(Pageable pageable) {
		return articleDao.findAll(pageable);
	}
}
