package net.minitt.hero.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.minitt.hero.blog.entity.Meta;

public interface MetaService {
	public Page<Meta> findByPage(Meta searchMeta, Pageable pageable);
	public List<Meta> findAll(Meta searchMeta);
	public List<Meta> findAllByIds(Set<Integer> ids);
	public Meta save(Meta meta);
	public Optional<Meta> findOneByName(String name);
	public void deleteById(Integer id);
	public void deleteByIds(Integer[] ids);
}
