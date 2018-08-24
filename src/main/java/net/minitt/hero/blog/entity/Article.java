package net.minitt.hero.blog.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import net.minitt.hero.core.base.BaseEntity;

@Entity
public class Article extends BaseEntity {
	public static final String FMT_TYPE_MD = "markdown";
	public static final String FMT_TYPE_HTML = "html";
	public static final String STATUS_PUBLISH = "publish";
	public static final String STATUS_DRAFT = "draft";

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String title;
	// 内容生成时的GMT unix时间戳
	private Long createdTime;
	// 内容更改时的GMT unix时间戳
	private Long modifiedTime;
	// 内容文字
	@Lob
	@Basic(fetch = FetchType.LAZY)
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "article_type", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "meta_id"))
	private Set<Meta> typeSet;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "article_tag", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "meta_id"))
	private Set<Meta> tagSet;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Set<Meta> getTypeSet() {
		return typeSet;
	}

	public void setTypeSet(Set<Meta> typeSet) {
		this.typeSet = typeSet;
	}

	public Set<Meta> getTagSet() {
		return tagSet;
	}

	public void setTagSet(Set<Meta> tagSet) {
		this.tagSet = tagSet;
	}

	public void addTag(Meta tag) {
		Set<Meta> tags = getTagSet();
		if (tags == null) {
			tags = new HashSet<Meta>();
			setTagSet(tags);
		}
		tags.add(tag);
		if (tag.getArticesByTag() == null)
			tag.setArticesByTag(new HashSet<Article>());
		tag.getArticesByTag().add(this);
	}

	public void addType(Meta type) {
		Set<Meta> types = getTypeSet();
		if (types == null) {
			types = new HashSet<Meta>();
			setTypeSet(types);
		}
		types.add(type);
		type.getArticesByType().add(this);
	}

	public void addTypes(Collection<Meta> types) {
		Set<Meta> thistypes = getTypeSet();
		if (thistypes == null) {
			thistypes = new HashSet<Meta>();
			setTypeSet(thistypes);
		}
		thistypes.addAll(types);
		for (Meta m : types) {
			m.getArticesByType().add(this);
		}
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Article))
			return false;
		else {
			Article article = (Article) obj;
			if (null == this.getId() || null == article.getId())
				return false;
			else
				return (this.getId().equals(article.getId()));
		}
	}

}
