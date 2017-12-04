package com.yhl.laoyou.modules.sys.service;

import com.yhl.laoyou.common.constant.ConfigConstant;
import com.yhl.laoyou.common.dto.MenuDTO;
import com.yhl.laoyou.common.dto.MenuParameterDTO;
import com.yhl.laoyou.common.service.BaseService;
import com.yhl.laoyou.modules.sys.dao.MenuDao;
import com.yhl.laoyou.modules.sys.entity.Menu;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *  菜单配置
 * @author 张博
 * @version 2017-5-3
 */
@Service
@Transactional(readOnly = false)
public class MenuService extends BaseService {

	@Autowired
	private MenuDao menuDao;


	@Autowired
	protected MongoTemplate mongoTemplate;

	public MenuParameterDTO getMenu(String menuType){
		Query query = new Query();
		query.addCriteria(Criteria.where("menuType").is(menuType));
		List<MenuParameterDTO> menuParameterDTOList = this.mongoTemplate.find(query, MenuParameterDTO.class,"Menu");
		parseMenuURL(menuParameterDTOList.get(0).getMenuDatas(),menuType);
		return menuParameterDTOList.get(0);
	}

	public int insertMenu(MenuParameterDTO menuParameterDTO){
		try {
			this.mongoTemplate.insert(menuParameterDTO, "Menu");
			return 1;
		}catch (Exception e){
			return 0;
		}
	}

	private void parseMenuURL(List<MenuDTO> menuDatas,String menuType){
		if(menuDatas.size()!=0)
		{
			for(MenuDTO menuDTO:menuDatas)
			{
				if(!menuDTO.getUrl().equals(""))
				{
					if(menuType.equals("menu0001")){
						menuDTO.setUrl("http://" + ConfigConstant.practitionerDomain + menuDTO.getUrl());
					}
				}
				parseMenuURL(menuDTO.getMenuDatas(),menuType);
			}

		}
	}

	public List<Menu> findByParentIdsLike(Menu menu){
		return menuDao.findAllList(menu);
	}

}
