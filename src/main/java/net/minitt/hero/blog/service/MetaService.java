package net.minitt.hero.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Meta;

public interface MetaService {
	public Page<Meta> findByPage(Meta searchMeta, Pageable pageable);
	public List<Meta> findAll(Meta searchMeta);
	public Meta save(Meta meta);
	public void deleteById(Integer id);
	public void deleteByIds(Integer[] ids);
}
