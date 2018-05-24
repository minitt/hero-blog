package net.minitt.hero.blog.entity;

import javax.persistence.Entity;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class SysConfig extends BaseEntity{
	private static final long serialVersionUID = 1L;

	// 配置名称
    private String key;
    private String type;
    private String options;
    // 配置值
    private String val;
    private Integer sort;
    // 配置描述
    private String description;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
}
