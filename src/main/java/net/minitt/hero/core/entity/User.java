package net.minitt.hero.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.minitt.hero.core.base.BaseEntity;

@Entity
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;

	/** 必须字段 begin **/
	// 用户名称
	@NotBlank
	@Column(unique=true)
    private String username;
	
	@NotBlank
	@Column(unique=true)
	private String telNo;
	
	// 用户的邮箱
	@NotBlank
	@Column(unique=true)
    private String email;

    /**
     * 密码
     * 永远不序列化密码
     */
    @JsonIgnore
    private String password;

    // 用户显示的名称
    @NotBlank
    private String screenName;

    // 用户注册时的GMT unix时间戳
    @NotNull
    private Long createdTime;

    // 最后活动时间
    private Long activatedTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
    private Role role;
    
    private Boolean isLocked;
    
    private Boolean isDel;
    
    /** 必须字段 end **/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getActivatedTime() {
		return activatedTime;
	}

	public void setActivatedTime(Long activatedTime) {
		this.activatedTime = activatedTime;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	
}
