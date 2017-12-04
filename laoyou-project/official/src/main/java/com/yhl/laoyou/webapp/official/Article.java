/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.webapp.official;

import com.google.common.collect.Lists;
import com.yhl.laoyou.common.config.Global;
import com.yhl.laoyou.common.persistence.DataEntity;
import com.yhl.laoyou.common.utils.SpringContextHolder;
import com.yhl.laoyou.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 文章Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Article{

	private static final long serialVersionUID = 1L;
	private String id;
	private Category category;// 分类编号
	private String title;	// 标题
	private String author;
	private String link;	// 外部链接
	private String color;	// 标题颜色（red：红色；green：绿色；blue：蓝色；yellow：黄色；orange：橙色）
	private String image;	// 文章图片
	private String keywords;// 关键字
	private String description;// 描述、摘要
	private Integer weight;	// 权重，越大越靠前
	private Date weightDate;// 权重期限，超过期限，将weight设置为0
	private Integer hits;	// 点击数
	private Integer share;	// 分享数
	private Integer praise;	// 点赞数
	private Integer comment;// 评论数
	private String posid;	// 推荐位，多选（1：首页焦点图；2：栏目页文章推荐；）

	private String customContentView;	// 自定义内容视图
   	private String viewConfig;	// 视图参数

	private ArticleData articleData;	//文章副表
	
	private Date beginDate;	// 开始时间
	private Date endDate;	// 结束时间

	private String remarks;	// 备注
	private Date createDate;	// 创建日期
	private Date updateDate;	// 更新日期
	private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	
	private static ServletContext context = SpringContextHolder.getBean(ServletContext.class);

	public Article() {
		super();
		this.weight = 0;
		this.hits = 0;
		this.share = 0;
		this.praise = 0;
		this.comment = 0;
		this.posid = "";
	}

	public Article(String id){
		this();
		this.id = id;
	}
	
	public Article(Category category){
		this();
		this.category = category;
	}

	public void prePersist(){
		//TODO 后续处理，暂不知有何用处
		//super.prePersist();
		articleData.setId(this.id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getShare() {
		return share;
	}
	
	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public void setShare(Integer share) {
		this.share = share;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
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

	public void setAuthor(String author) {
		this.author = author;
	}

    @Length(min=0, max=255)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

	@Length(min=0, max=50)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Length(min=0, max=255)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
        this.image = image;//CmsUtils.formatImageSrcToDb(image);
	}

	@Length(min=0, max=255)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Length(min=0, max=255)
	public String getDescription() {
		return description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Length(min=0, max=10)
	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

    public String getCustomContentView() {
        return customContentView;
    }

    public void setCustomContentView(String customContentView) {
        this.customContentView = customContentView;
    }

    public String getViewConfig() {
        return viewConfig;
    }

    public void setViewConfig(String viewConfig) {
        this.viewConfig = viewConfig;
    }

	public ArticleData getArticleData() {
		return articleData;
	}

	public void setArticleData(ArticleData articleData) {
		this.articleData = articleData;
	}

	public List<String> getPosidList() {
		List<String> list = Lists.newArrayList();
		if (posid != null){
			for (String s : StringUtils.split(posid, ",")) {
				list.add(s);
			}
		}
		return list;
	}

	public void setPosidList(List<String> list) {
		posid = ","+ StringUtils.join(list, ",")+",";
	}

   	public String getUrl() {
        return getUrlDynamic(this);
   	}

   	public String getImageSrc() {
        return formatImageSrcToWeb(this.image);
   	}
	

   	/**
     * 获得文章动态URL地址
   	 * @param article
   	 * @return url
   	 */
    private static String getUrlDynamic(Article article) {
        if(StringUtils.isNotBlank(article.getLink())){
            return article.getLink();
        }
        StringBuilder str = new StringBuilder();
        str.append(context.getContextPath()).append(Global.getFrontPath());
        str.append("/view-").append(article.getCategory().getId()).append("-").append(article.getId()).append(Global.getUrlSuffix());
        return str.toString();
    }

    /**
     * 从图片地址中加入ContextPath地址
   	 * @param src
   	 * @return src
   	 */
    private static String formatImageSrcToWeb(String src) {
        if(StringUtils.isBlank(src)) return src;
        if(src.startsWith(context.getContextPath() + "/userfiles")){
            return src;
        }else{
            return context.getContextPath()+src;
        }
    }
    
}


