package net.minitt.hero.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Article;
import net.minitt.hero.blog.entity.Search;

public interface SearchService {
	/**
     * 新增 ES 查询信息
     * @param search
     * @return
     */
	Integer saveSearch(Search search);
	Integer saveSearch(Article article);

    /**
     * 搜索词搜索，分页返回查询信息
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    Page<Search> search(Pageable pageable, String keyword);
}
