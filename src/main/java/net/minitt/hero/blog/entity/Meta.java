package net.minitt.hero.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class Meta extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 名称
	private String name;
	// 项目缩略名
	private String slug;
	// 项目类型
	private String type;
	// 选项描述
	private String description;
	// 项目排序
	private Integer sort;
	// 文章数
	private Integer count;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="matas")
	private Set<Article> artices;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<Article> getArtices() {
		return artices;
	}

	public void setArtices(Set<Article> artices) {
		this.artices = artices;
	}

}
