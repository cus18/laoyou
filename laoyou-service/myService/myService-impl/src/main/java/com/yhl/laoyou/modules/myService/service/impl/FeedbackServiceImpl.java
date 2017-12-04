package com.yhl.laoyou.modules.myService.service.impl;

import com.yhl.laoyou.common.dto.practitioner.Settings.FeedbackDTO;
import com.yhl.laoyou.modules.myService.service.FeedbackService;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zbm84 on 2017/5/11.
 */
@Service
@Transactional(readOnly = false)
public class FeedbackServiceImpl implements FeedbackService {


    @Autowired
    protected MongoTemplate mongoTemplate;


    @Override
    public int insertFeedback(FeedbackDTO feedbackDto) {
        try {
            feedbackDto.setId(UUIDUtil.getUUID());
            feedbackDto.setCreateDate(new Date());
            this.mongoTemplate.insert(feedbackDto, "feedback");
            return 1;
        }catch (Exception e){

            return 0;
        }
    }
}
