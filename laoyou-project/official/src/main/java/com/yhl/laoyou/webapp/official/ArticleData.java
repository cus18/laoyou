/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.webapp.official;

import com.yhl.laoyou.common.config.Global;
import com.yhl.laoyou.common.persistence.DataEntity;
import com.yhl.laoyou.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-01-15
 */
public class ArticleData {

	private static final long serialVersionUID = 1L;
	private String content;	// 内容
	private String copyfrom;// 来源
	private String relation;// 相关文章
	private String id;
	private String allowComment;// 是否允许评论
	private String remarks;	// 备注
	private Date createDate;	// 创建日期
	private Date updateDate;	// 更新日期
	private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	private Article article;
	
	public ArticleData() {
		super();
		this.allowComment = Global.YES;
	}
	
	public ArticleData(String id){
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min=0, max=255)
	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	@Length(min=0, max=255)
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Length(min=1, max=1)
	public String getAllowComment() {
		return allowComment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}