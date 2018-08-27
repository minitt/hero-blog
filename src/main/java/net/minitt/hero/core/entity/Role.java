package net.minitt.hero.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import net.minitt.hero.core.base.BaseEntity;

@Entity
public class Role extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String desc;
	private Boolean allowDel;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
	private Set<Resource> resourceSet;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Boolean getAllowDel() {
		return allowDel;
	}
	public void setAllowDel(Boolean allowDel) {
		this.allowDel = allowDel;
	}
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}
	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}
}
