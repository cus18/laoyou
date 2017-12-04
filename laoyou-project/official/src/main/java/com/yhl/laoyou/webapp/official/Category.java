/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.webapp.official;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.collect.Lists;
import com.yhl.laoyou.common.config.Global;
import com.yhl.laoyou.common.persistence.TreeEntity;
import com.yhl.laoyou.common.utils.Reflections;
import com.yhl.laoyou.common.utils.SpringContextHolder;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 栏目Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Category{

    public static final String DEFAULT_TEMPLATE = "frontList";
	private String remarks;	// 备注
	private Date createDate;	// 创建日期
	private Date updateDate;	// 更新日期
	private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

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
	private static final long serialVersionUID = 1L;
//	private Category parent;// 父级菜单
//	private String parentIds;// 所有父级编号
	private String module; 	// 栏目模型（article：文章；picture：图片；download：下载；link：链接；special：专题）
//	private String name; 	// 栏目名称
	private String image; 	// 栏目图片
	private String href; 	// 链接
	private String target; 	// 目标（ _blank、_self、_parent、_top）
	private String description; 	// 描述，填写有助于搜索引擎优化
	private String keywords; 	// 关键字，填写有助于搜索引擎优化
//	private Integer sort; 		// 排序（升序）
	private String inMenu; 		// 是否在导航中显示（1：显示；0：不显示）
	private String inList; 		// 是否在分类页中显示列表（1：显示；0：不显示）
	private String showModes; 	// 展现方式（0:有子栏目显示栏目列表，无子栏目显示内容列表;1：首栏目内容列表；2：栏目第一条内容）
	private String allowComment;// 是否允许评论
	private String isAudit;	// 是否需要审核
	private String customListView;		// 自定义列表视图
	private String customContentView;	// 自定义内容视图
    private String viewConfig;	// 视图参数
	private String id;
    
    private Date beginDate;	// 开始时间
    private Date endDate;	// 结束时间
    private String cnt;//信息量
    private String hits;//点击量
	
	private List<Category> childList = Lists.newArrayList(); 	// 拥有子分类列表

	private static ServletContext context = SpringContextHolder.getBean(ServletContext.class);
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

	public Category(){
		super();
		this.module = "";
		this.sort = 30;
		this.inMenu = Global.HIDE;
		this.inList = Global.SHOW;
		this.showModes = "0";
		this.allowComment = Global.NO;
		this.delFlag = DEL_FLAG_NORMAL;
		this.isAudit = Global.NO;
	}

	public Category(String id){
		this();
		this.id = id;
	}

	public Category(String id, Site site){
		this();
		this.id = id;
	}


	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//	@JsonBackReference
//	@NotNull
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

//	@Length(min=1, max=255)
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}

	@Length(min=0, max=20)
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

//	@Length(min=0, max=100)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	@Length(min=0, max=255)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Length(min=0, max=255)
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Length(min=0, max=20)
	public String getTarget() {
		return target;
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

	public void setTarget(String target) {
		this.target = target;
	}

	@Length(min=0, max=255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=255)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
//	@NotNull
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

	@Length(min=1, max=1)
	public String getInMenu() {
		return inMenu;
	}

	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	@Length(min=1, max=1)
	public String getInList() {
		return inList;
	}

	public void setInList(String inList) {
		this.inList = inList;
	}

	@Length(min=1, max=1)
	public String getShowModes() {
		return showModes;
	}

	public void setShowModes(String showModes) {
		this.showModes = showModes;
	}
	
	@Length(min=1, max=1)
	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	@Length(min=1, max=1)
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getCustomListView() {
		return customListView;
	}

	public void setCustomListView(String customListView) {
		this.customListView = customListView;
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

	public List<Category> getChildList() {
		return childList;
	}

	public void setChildList(List<Category> childList) {
		this.childList = childList;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public static void sortList(List<Category> list, List<Category> sourcelist, String parentId){
		for (int i=0; i<sourcelist.size(); i++){
			Category e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					Category child = sourcelist.get(j);
					if (child.getParent()!=null && child.getParent().getId()!=null
							&& child.getParent().getId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}

	public String getIds() {
		return (this.getParentIds() !=null ? this.getParentIds().replaceAll(",", " ") : "")
				+ (this.getId() != null ? this.getId() : "");
	}

	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	public static boolean isRoot(String id){
		return id != null && id.equals("1");
	}

   	public String getUrl() {
        return getUrlDynamic(this);
   	}

   	/**
     * 获得栏目动态URL地址
   	 * @param category
   	 * @return url
   	 */
    private static String getUrlDynamic(Category category) {
        if(StringUtils.isNotBlank(category.getHref())){
            if(!category.getHref().contains("://")){
                return context.getContextPath()+ Global.getFrontPath()+category.getHref();
            }else{
                return category.getHref();
            }
        }
        StringBuilder str = new StringBuilder();
        str.append(context.getContextPath()).append(Global.getFrontPath());
        str.append("/list-").append(category.getId()).append(Global.getUrlSuffix());
        return str.toString();
    }

	protected Category parent;	// 父级编号
	protected String parentIds; // 所有父级编号
	protected String name; 	// 机构名称
	protected Integer sort;		// 排序


	@Length(min=1, max=2000)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		String id = null;
		if (parent != null){
			id = (String) Reflections.getFieldValue(parent, "id");
		}
		return StringUtils.isNotBlank(id) ? id : "0";
	}

}