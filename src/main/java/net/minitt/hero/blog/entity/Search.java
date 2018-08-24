package net.minitt.hero.blog.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1"
@Document(indexName = "hero-blog", type = "search", shards = 5, replicas = 0, refreshInterval = "-1")
public class Search implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	/**
     * 名称
     * ik_max_word：会将文本做最细粒度的拆分，例如「中华人民共和国国歌」会被拆分为「中华人民共和国、中华人民、中华、华人、人民共和国、人民、人、民、共和国、共和、和、国国、国歌」，会穷尽各种可能的组合；
	 * ik_smart：会将文本做最粗粒度的拆分，例如「中华人民共和国国歌」会被拆分为「中华人民共和国、国歌」；
     */
	@Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String title;

    /**
     * 内容
     */
    @Field(type = FieldType.text, fielddata = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String content;
    
    /**
     * 分类
     */
    @Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String categories;
    
    /**
     * 标签
     */
    @Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String tags;
    
    /**
     * 创建时间
     */
    @Field(type = FieldType.Long, index = false)
    private Long createdTime;
    
    /**
     * 修改时间
     */
    @Field(type = FieldType.Long, index = false)
    private Long modifiedTime;
    
    /**
     * 创建人
     */
    @Field(type = FieldType.text, index = false)
    private String author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
    
}
