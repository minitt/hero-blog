package net.minitt.hero.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import net.minitt.hero.core.base.BaseEntity;

@Entity
public class Resource extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final Integer RESOURCE_MENU_TYPE = 1;
	public static final Integer RESOURCE_FUN_TYPE = 2;
	
	private String name;
	private String path;
	private String icon;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id")
	private Resource parent;
	private Integer sort;
	private Integer type;
	private String auth;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="resourceSet")
	private Set<Role> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
