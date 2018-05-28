package net.minitt.hero.blog.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Article;

public interface ArticleService {
	public Page<Article> findByPage(Article searchArticle, Pageable pageable);
	public Page<Article> findByPage(Pageable pageable);
	/**
	 * 保存方法
	 * @param article 文章
	 * @param typeArr 分类
	 * @param tagnameArr 标签
	 * @return
	 */
	public Article save(Article article,Set<Integer> typeArr,Set<String> tagnameArr);
	/**
	 * 草稿
	 * @param article 文章
	 * @param typeArr 分类
	 * @param tagnameArr 标签
	 * @return
	 */
	public Article draft(Article article,Set<Integer> typeArr,Set<String> tagnameArr);
	/**
	 * 保存方法
	 * @param article 文章
	 * @param typeArr 分类
	 * @param tagnameArr 标签
	 * @param status 状态(草稿，发布)
	 * @return
	 */
	public Article save(Article article,Set<Integer> typeArr,Set<String> tagnameArr,String status);
	public Optional<Article> findById(Integer id);
	public void deleteById(Integer id);
	public void deleteByIds(Integer[] ids);
}
