/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.dto.QuestionnaireInfoDTO;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 信息DAO接口
 */
@MyBatisDao
@Repository
public interface QuestionnaireInfoDao {



	 int insert(QuestionnaireInfoDTO questionnaireInfoDTO);

	 QuestionnaireInfoDTO findAllList(QuestionnaireInfoDTO questionnaireInfoDTO);

}
