package com.yhl.laoyou.modules.courseService.service.impl;

import com.yhl.laoyou.common.constant.StatusType;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.course.*;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.DateUtils;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.courseService.dao.LiveCourseDao;
import com.yhl.laoyou.modules.sys.entity.Dict;
import com.yhl.laoyou.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yhl.laoyou.modules.courseService.service.LiveCourseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunxiao on 2017/8/8.
 */

@Service
@Transactional(readOnly = false)
public class LiveCourseServiceImpl implements LiveCourseService{

    @Autowired
    LiveCourseDao liveCourseDao;

    @Autowired
    DictService dictService;

    @Override
    public Page getLiveCourseByInfo(PageParamDTO<String> pageParamDTO,String[] status) {
        LiveCourseDTO liveCourseDTO = new LiveCourseDTO();
        liveCourseDTO.setLiveCourseStatusArray(status);
        Page<LiveCourseDTO> page = new Page(Integer.parseInt(pageParamDTO.getPageNo()),Integer.parseInt(pageParamDTO.getPageSize()));
        Page<LiveCourseDTO> p = liveCourseDao.getLiveCourseByInfo(liveCourseDTO,page);
        return p;
    }

    @Override
    public List<LiveCourseDTO> getAllLiveCourseByInfo(String[] status) {
        LiveCourseDTO liveCourseDTO = new LiveCourseDTO();
        liveCourseDTO.setLiveCourseStatusArray(status);
        return liveCourseDao.getAllLiveCourseByInfo(liveCourseDTO);
    }

    @Override
    public LiveCourseDTO getLiveBroadCastDetail(String elderId,LiveCourseDTO dto) {
        dto = liveCourseDao.getLiveBroadCastDetail(dto);
        LiveCourseRegisterDTO liveCourseRegisterDTO = new LiveCourseRegisterDTO();
        if(dto != null){
            liveCourseRegisterDTO.setLiveCourseId(dto.getLiveCourseId());
            liveCourseRegisterDTO.setElderId(elderId);
            liveCourseRegisterDTO = liveCourseDao.getLiveCourseRegister(liveCourseRegisterDTO);
        }
        dto.setLiveCourseRegisterStatus(liveCourseRegisterDTO != null?"yes":"no");
        return dto;
    }

    @Override
    public void registerLiveBroadCast(LiveCourseRegisterDTO dto) {
        liveCourseDao.saveLiveCourseRegister(dto);
    }

    @Override
    public List<OnlineCourseDTO> getOnlineCourseList(PageParamDTO<String> pageParamDTO) {
        List<OnlineCourseDTO> returnList = new ArrayList<>();
        if("page".equals(pageParamDTO.getRequestData())){
            Page page = new Page(Integer.parseInt(pageParamDTO.getPageNo()),Integer.parseInt(pageParamDTO.getPageSize()));
            OnlineCourseDTO onlineCourseDTO = new OnlineCourseDTO();
            returnList = liveCourseDao.getOnlineCourseList(onlineCourseDTO,page).getList();
        }else{
            if(StringUtils.isNotNull(pageParamDTO.getRequestData())){
                Dict dict = new Dict();
                dict.setType("video");
                dict.setValue(pageParamDTO.getRequestData());
                List<Dict> dictList1 = dictService.findListByInfo(dict);
                if(dictList1!=null && dictList1.size()>0){
                    List lableIdList = new ArrayList();
                    dict.setValue("");
                    dict.setParentId(dictList1.get(0).getId());
                    List<Dict> dictList2 = dictService.findListByInfo(dict);
                    if(dictList2!=null && dictList2.size()>0){
                        for(Dict temp : dictList2){
                            lableIdList.add(temp.getId());
                        }
                    }else{
                        for(Dict temp : dictList1){
                            lableIdList.add(temp.getId());
                        }
                    }
                    OnlineCourseDTO onlineCourseDTO = new OnlineCourseDTO();
                    onlineCourseDTO.setOnlineCourseLabelIds(lableIdList);
                    returnList = liveCourseDao.getAllOnlineCourseListByInfo(onlineCourseDTO);
                }
            }else{
                OnlineCourseDTO onlineCourseDTO = new OnlineCourseDTO();
                returnList = liveCourseDao.getAllOnlineCourseListByInfo(onlineCourseDTO);
            }
        }
        return returnList;
    }

    @Override
    public OnlineCourseDTO getOnlineCourseDetail(OnlineCourseDTO onlineCourseDTO) {
        OnlineCourseDTO result = liveCourseDao.getOnlineCourse(onlineCourseDTO);
        OnlineCourseDataDTO onlineCourseDataDTO = new OnlineCourseDataDTO();
        onlineCourseDataDTO.setOnlineCourseId(onlineCourseDTO.getOnlineCourseId());
        List dataList = liveCourseDao.getOnlineCourseDataList(onlineCourseDataDTO);
        result.setOnLineCourseDataDTOList(dataList);
        return result;
    }

    @Override
    public Page getOnlineCourseDiscuss(PageParamDTO<String> pageParamDTO) {
        OnlineCourseDiscussDTO dto = new OnlineCourseDiscussDTO();
        dto.setOnlineCourseId(Integer.parseInt(pageParamDTO.getRequestData()));
        Page page = new Page(Integer.parseInt(pageParamDTO.getPageNo()),Integer.parseInt(pageParamDTO.getPageSize()));
        return liveCourseDao.getOnlineCourseDiscuss(dto,page);
    }

    @Override
    public void createOnlineCourseDiscuss(OnlineCourseDiscussDTO onlineCourseDiscussDTO) {
        liveCourseDao.addOnlineCourseDiscuss(onlineCourseDiscussDTO);
    }

    @Override
    public void updateLiveCourse(LiveCourseDTO liveCourseDTO) {
        liveCourseDao.updateLiveCourse(liveCourseDTO);
    }

    @Override
    public List<OnlineCourseMyCourseDTO> getMyOnlineCourse(OnlineCourseMyCourseDTO dto,PageParamDTO<String> pageParamDTO) {
        if(pageParamDTO.getRequestData().indexOf(",")>0){
            Page<OnlineCourseMyCourseDTO> page = new Page(Integer.parseInt(pageParamDTO.getPageNo()),Integer.parseInt(pageParamDTO.getPageSize()));
            dto.setType(pageParamDTO.getRequestData().split(",")[1]);
            Page<OnlineCourseMyCourseDTO> p = liveCourseDao.getMyOnlineCourse(dto,page);
            for(OnlineCourseMyCourseDTO temp : p.getList()){
                OnlineCourseDTO onlineCourseDTO = new OnlineCourseDTO();
                onlineCourseDTO.setOnlineCourseId(temp.getCourseId());
                temp.setOnlineCourseDTO(liveCourseDao.getOnlineCourse(onlineCourseDTO));
            }
            return p.getList();
        }else{
            dto.setType(pageParamDTO.getRequestData());
            List<OnlineCourseMyCourseDTO> list = liveCourseDao.getAllMyOnlineCourseByInfo(dto);
            for(OnlineCourseMyCourseDTO temp : list){
                OnlineCourseDTO onlineCourseDTO = new OnlineCourseDTO();
                onlineCourseDTO.setOnlineCourseId(temp.getCourseId());
                temp.setOnlineCourseDTO(liveCourseDao.getOnlineCourse(onlineCourseDTO));
            }
            return list;
        }
    }

    @Override
    public List<OnlineCourseDTO> findOnlineCoursePage(PageParamDTO<String> pageParamDTO) {
        return liveCourseDao.findOnlineCoursePage(pageParamDTO);
    }
}
