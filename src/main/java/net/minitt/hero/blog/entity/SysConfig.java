package net.minitt.hero.blog.entity;

import javax.persistence.Entity;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class SysConfig extends BaseEntity{
	private static final long serialVersionUID = 1L;

	// 配置名称
    private String key;
    // 配置值
    private String val;
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
    
}
