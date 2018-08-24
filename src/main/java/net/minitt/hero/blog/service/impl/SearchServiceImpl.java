package net.minitt.hero.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minitt.hero.blog.dao.SearchDao;
import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.entity.Search;
import net.minitt.hero.blog.service.SearchService;
import net.minitt.hero.core.utils.HeroUtils;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDao searchDao;

	@Override
	@Transactional(readOnly = false)
	public Integer saveSearch(Search search) {
		Search searchResult = searchDao.save(search);
        return searchResult.getId();
	}

	@Override
	public Page<Search> search(Pageable pageable, String keyword) {
		return searchDao.findByTitleLikeOrContentLike(keyword, keyword, pageable);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Integer saveSearch(Article article) {
		Search search = new Search();//搜索ES
		search.setId(article.getId());
		search.setTitle(article.getTitle());
		if(article.getAuthor()!=null) {
			search.setAuthor(article.getAuthor().getScreenName());
		}
		search.setCategories(article.getCategories());
		search.setContent(HeroUtils.htmlToText(HeroUtils.mdToHtml(article.getContent())));
		search.setCreatedTime(article.getCreatedTime());
		search.setModifiedTime(article.getModifiedTime());
		search.setTags(article.getTags());
		Search searchResult = searchDao.save(search);
        return searchResult.getId();
	}
}
