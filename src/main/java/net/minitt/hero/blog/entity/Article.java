package net.minitt.hero.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class Article extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String title;
	// 内容缩略名
	private String slug;
	// 内容生成时的GMT unix时间戳
	private Long createdTime;
	// 内容更改时的GMT unix时间戳
	private Long modifiedTime;
	// 内容文字
	private String content;
	// 内容作者
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;
	// 点击次数
	private Integer hits;
	// 内容类别
	private String type;
	// 内容类型，markdown或者html
	private String fmtType;
	// 文章缩略图
	private String thumbImg;
	// 标签列表
	private String tags;
	// 分类列表
	private String categories;
	// 内容状态
	private String status;
	// 内容所属评论数
	private Integer commentsNum;
	// 是否允许评论
	private Boolean allowComment;
	// 是否允许ping
	private Boolean allowPing;
	// 允许出现在聚合中
	private Boolean allowFeed;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="relationships",joinColumns=
            @JoinColumn(name="article_id"),
        inverseJoinColumns=@JoinColumn(name="meta_id"))
	private Set<Meta> matas;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFmtType() {
		return fmtType;
	}

	public void setFmtType(String fmtType) {
		this.fmtType = fmtType;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}

	public Boolean getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Boolean allowComment) {
		this.allowComment = allowComment;
	}

	public Boolean getAllowPing() {
		return allowPing;
	}

	public void setAllowPing(Boolean allowPing) {
		this.allowPing = allowPing;
	}

	public Boolean getAllowFeed() {
		return allowFeed;
	}

	public void setAllowFeed(Boolean allowFeed) {
		this.allowFeed = allowFeed;
	}

	public Set<Meta> getMatas() {
		return matas;
	}

	public void setMatas(Set<Meta> matas) {
		this.matas = matas;
	}

}
