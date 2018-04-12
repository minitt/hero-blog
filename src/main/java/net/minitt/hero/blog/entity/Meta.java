package net.minitt.hero.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class Meta extends BaseEntity {
	public interface WithoutArticesView extends BaseJsonView {};
	public interface WithArticesView extends WithoutArticesView {};
	
	private static final long serialVersionUID = 1L;
	// 名称
	@NotBlank
	@JsonView(WithoutArticesView.class)
	private String name;
	// 项目缩略名
	@JsonView(WithoutArticesView.class)
	private String slug;
	// 项目类型
	@NotBlank
	@JsonView(WithoutArticesView.class)
	private String type;
	// 选项描述
	@JsonView(WithoutArticesView.class)
	private String description;
	// 项目排序
	@JsonView(WithoutArticesView.class)
	private Integer orderby;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="typeSet")
	@JsonView(WithArticesView.class)
	private Set<Article> articesByType;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="tagSet")
	@JsonView(WithArticesView.class)
	private Set<Article> articesByTag;

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

	public Set<Article> getArticesByType() {
		return articesByType;
	}

	public void setArticesByType(Set<Article> articesByType) {
		this.articesByType = articesByType;
	}

	public Set<Article> getArticesByTag() {
		return articesByTag;
	}

	public void setArticesByTag(Set<Article> articesByTag) {
		this.articesByTag = articesByTag;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Meta)) return false;
		else {
			Meta meta = (Meta) obj;
			if (null == this.getId() || null == meta.getId()) return false;
			else return (this.getId().equals(meta.getId()));
		}
	}
}
