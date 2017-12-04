package com.yhl.laoyou.modules.myService.service.impl;

import com.alibaba.fastjson.JSON;
import com.yhl.laoyou.common.dto.activity.ActivityDTO;
import com.yhl.laoyou.common.dto.activity.ActivityDiscussDTO;
import com.yhl.laoyou.common.dto.activity.ActivityEasemobGroupInfo;
import com.yhl.laoyou.common.dto.activity.AttendedActivityDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.activityService.DTO.ActivityEasemobGroupInfoDTO;
import com.yhl.laoyou.modules.activityService.DTO.ActivityEasemobGroupUserDTO;
import com.yhl.laoyou.modules.activityService.entity.*;
import com.yhl.laoyou.modules.myService.dao.*;
import com.yhl.laoyou.modules.myService.service.ActivityService;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindTemplateDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindUserDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindEntity;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindUserEntity;
import com.yhl.laoyou.modules.sys.dao.SysElderUserDao;
import com.yhl.laoyou.modules.sys.dao.SysHospitalUserDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zbm84 on 2017/7/24.
 */
@Service
@Transactional(readOnly = false)
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityUserDao activityUserDao;

    @Autowired
    private ActivityDiscussDao activityDiscussDao;

    @Autowired
    private ActivityEasemobGroupDao activityEasemobGroupDao;

    @Autowired
    private SysHospitalUserDao sysHospitalUserDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityFavoriteDao activityFavoriteDao;

    @Autowired
    private RemindTemplateDao remindTemplateDao;

    @Autowired
    private SysElderUserDao sysElderUserDao;

    @Autowired
    private RemindDao remindDao;

    @Autowired
    private RemindUserDao remindUserDao;

    @Override
    public ActivityDTO getActivity(String elderID) {
        return activityDao.getActivityList(elderID,null).get(0);
    }

    @Override
    public List<ActivityDTO> getActivityList(String elderID, String pageNo,String activityType) {
        List<ActivityDTO> result = activityDao.getMyActivityListByElderID(elderID,Integer.parseInt(pageNo)*10,activityType);
        return result;
    }

    @Override
    public Integer addActivity(Activity activity,User user) {
        activity.setSysUserID(user.getId());
        if(user.getSysElderUserDTO()!=null){
            activity.setUserType("0");
        }else{
            activity.setUserType("1");
        }
        activityDao.addActivity(activity);
        return activity.getId();
    }

    @Override
    public List<AttendedActivityDTO> getActivityByElderUser(String elderID) {
        List<AttendedActivityDTO> list = activityDao.getActivityListByElderID(elderID);
        List<AttendedActivityDTO> result = new ArrayList<>();
        for (AttendedActivityDTO a : list) {
            a.setActivityAttendedNum(activityUserDao.getActivityCountByID(a.getActivityId(), null).toString());
            result.add(a);
        }
        return result;
    }

    @Override
    public List<ActivityDiscussDTO> getActivityDiscuss(String id,Integer page) {
        return activityDiscussDao.getActivityDiscussList(id,page);
    }

    @Override
    public Integer addActivityDiscuss(ActivityDiscussDTO activityDiscussDTO) {
        ActivityDiscuss activityDiscuss = new ActivityDiscuss();
        activityDiscuss.setContent(activityDiscussDTO.getDiscussContent());
        activityDiscuss.setSysUserElderID(activityDiscussDTO.getElderId());
        activityDiscuss.setActivityID(activityDiscussDTO.getActivityId());
        return activityDiscussDao.addActivityDiscuss(activityDiscuss);
    }

    @Override
    public Integer updateActivtyStatus() {
        List<Activity> list = activityDao.getActivityListByTask();
        for (Activity a : list) {
            String status = "ongoing";
            if (a.getStatus().equals("ongoing")) {
                status = "end";
            }
            activityDao.updateActivityStatus(status, a.getId().toString());
        }
        return 0;
    }

    @Override
    public Integer getActivityAttendStatus(String activityID, String elderID) {
        return activityUserDao.getActivityCountByID(activityID, elderID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addActivityUser(String activityID, List<String> elderIDs) {
        ActivityUser activityUser = new ActivityUser();
        activityUser.setActivityID(activityID);
        ActivityDTO activity = activityDao.getActivityList(activityID,null).get(0);
        Integer nums=activityUserDao.getActivityCountByID(activityID, null);
        if(nums!=0&&nums==activity.getPeopleNum()){
            return "max";
        }
        boolean ifGroup=activity.getActivityEasemobGroupID()!=null && !activity.getActivityEasemobGroupID().equals("");
        for (String eID : elderIDs) {
            activityUser.setSysElderUserID(eID);
            activityUserDao.addActivityUser(activityUser);

            //将用户加入群组
            if(ifGroup){
                String elderEasemobID = sysElderUserDao.getSysElderUser(eID).getEasemobID();
                ActivityEasemobGroup activityEasemobGroup = activityEasemobGroupDao.searchActivityEasemobGroupByGroupID(activity.getActivityEasemobGroupID());
                boolean a=EasemobService.joinEasemobGroup(activityEasemobGroup.getGroupId(),elderEasemobID);
                if(a){
                    activityEasemobGroup.setMembers(activityEasemobGroup.getMembers().equals("")?elderEasemobID:activityEasemobGroup.getMembers()+","+elderEasemobID);
                    activityEasemobGroupDao.updateActivityEasemobGroup(activityEasemobGroup);
                }
            }

        }
        activity = activityDao.getActivityList(activityID,null).get(0);
        return activity.getActivityEasemobGroupID();
    }

    @Override
    public void createActivityEasemobGroup(String activityID, User user) {
        ActivityDTO activity = activityDao.getActivityList(activityID,null).get(0);
        String groupName = activity.getActivityName();
        String desc = activity.getActivityDetailInfo();
        String owner="";
        if(user.getSysElderUserDTO()!=null) {
            owner = user.getSysElderUserDTO().getEasemobID();
        }else{
            owner=user.getSysHospitalUserDTO().getEasemobID();
        }
        String result = EasemobService.createEasemobGroup(groupName, desc, owner, owner);
        Map<String, Object> map = (Map<String, Object>) JSON.parse(result.split(">>>>")[1]);
        ActivityEasemobGroup activityEasemobGroup = new ActivityEasemobGroup();
        activityEasemobGroup.setGroupId(((Map<String, Object>) JSON.parse(map.get("data").toString())).get("groupid").toString());
        activityEasemobGroup.setGroupName(groupName);
        activityEasemobGroup.setOwner(owner);
        activityEasemobGroup.setMembers(owner);
        activityEasemobGroup.setDesc(activity.getActivityDetailInfo());
        Integer res = activityEasemobGroupDao.addActivityEasemobGroup(activityEasemobGroup);
        activityDao.updateActivityEasemobGroup(activityID,activityEasemobGroup.getId().toString());
    }



    @Override
    public void joinActivityEasemobGroup(String activityID, User user) {
        ActivityDTO activity = activityDao.getActivityList(activityID,null).get(0);
        String elderEasemobID=user.getSysElderUserDTO().getEasemobID();
//        ActivityEasemobGroup activityEasemobGroup=activityEasemobGroupDao.searchActivityEasemobGroupByID(activity.getActivityEasemobGroupID());
        boolean a=EasemobService.joinEasemobGroup(activity.getActivityEasemobGroupID(),elderEasemobID);
        if(a){
            ActivityEasemobGroup activityEasemobGroup=new ActivityEasemobGroup();
            activityEasemobGroup.setMembers(activityEasemobGroup.getMembers().equals("")?elderEasemobID:activityEasemobGroup.getMembers()+","+elderEasemobID);
            activityEasemobGroupDao.updateActivityEasemobGroup(activityEasemobGroup);
        }
    }

    @Override
    public List<ActivityEasemobGroupInfo> getActivityEasemobGroupUserList(String activityID) {
        ActivityDTO activity = activityDao.getActivityList(activityID,null).get(0);
        ActivityEasemobGroup activityEasemobGroup=activityEasemobGroupDao.searchActivityEasemobGroupByID(activity.getActivityEasemobGroupID());
        String[] memeber=activityEasemobGroup.getMembers().split(",");
        List<ActivityEasemobGroupInfo> list=new ArrayList<>();
        for (String m:memeber) {
            SysElderUserDTO sysElderUserDTO=sysElderUserDao.getSysElderUserByEasemobID(m);
            User user=userDao.get(sysElderUserDTO.getSysUserID());
            ActivityEasemobGroupInfo activityEasemobGroupInfo=new ActivityEasemobGroupInfo();
            activityEasemobGroupInfo.setUserName(user.getName());
            activityEasemobGroupInfo.setUserPhoto(user.getPhoto());
            list.add(activityEasemobGroupInfo);
        }
        return list;
    }

    @Override
    public List<ActivityDTO> getActivityListBySearch(String search) {
        return activityDao.getActivityList(null,search);
    }

    @Override
    public void addActivityFavorite(String activityID,String sysElderUserID) {
        ActivityFavorite activityFavorite=new ActivityFavorite();
        activityFavorite.setActivityID(activityID);
        activityFavoriteDao.addActivityFavorite(activityFavorite);
    }

    @Override
    public void delActivityFavorite(String activityID,String sysElderUserID) {
        ActivityFavorite activityFavorite=new ActivityFavorite();
        activityFavorite.setActivityID(activityID);
        activityFavoriteDao.addActivityFavorite(activityFavorite);
    }

    @Override
    public List<ActivityDTO> activityListByFirstPage(String hospitalID) {
        List<ActivityDTO> list=activityDao.getMyHospitalActivityListByHospitalID(hospitalID);
        if(list.size()==0){
            list=activityDao.getMyActivityListByElderID(null,2,null);
        }else if(list.size()==1){
            ActivityDTO activityDTO=activityDao.getMyActivityListByElderID(null,2,null).get(1);
            list.add(activityDTO);
        }
        return list;
    }

    @Override
    public List<ActivityDTO> getMyFavoriteActivityList(String sysElderUserID) {
        return activityDao.getMyFavoriteActivityList(sysElderUserID);
    }

    @Override
    public List<ActivityEasemobGroup> getUserActivityGroupInfo(String elderEasemobID) {

        return activityEasemobGroupDao.getUserActivityEasemobGroupList(elderEasemobID);
    }

    @Override
    public List<ActivityEasemobGroup> getUserActivityEasemobGroupList(String elderEasemobID) {
        return activityEasemobGroupDao.getUserActivityEasemobGroupList(elderEasemobID);
    }

    @Override
    public ActivityEasemobGroupInfoDTO getActivityEasemobGroupUsers(String groupID) {
        ActivityEasemobGroup activityEasemobGroup=activityEasemobGroupDao.searchActivityEasemobGroupByGroupID(groupID);
        List<ActivityEasemobGroupUserDTO> list=new ArrayList<>();
        String[] members=activityEasemobGroup.getMembers().split(",");
        for (String m:members) {
            SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElderUserByEasemobID(m);
            String sysUserID="";
            if(sysElderUserDTO==null){
                SysHospitalUserDTO sysHospitalUserDTO=sysHospitalUserDao.getSysHospitalUserByEasemobID(activityEasemobGroup.getOwner());
                sysUserID=sysHospitalUserDTO.getSysUserID();
            }else {
                sysUserID = sysElderUserDTO.getSysUserID();
            }
            User user = userDao.get(sysUserID);
            ActivityEasemobGroupUserDTO activityEasemobGroupUserDTO=new ActivityEasemobGroupUserDTO();
            activityEasemobGroupUserDTO.setSysUserID(sysUserID);
            activityEasemobGroupUserDTO.setName(user.getName());
            activityEasemobGroupUserDTO.setPhoto(user.getPhoto());
            list.add(activityEasemobGroupUserDTO);
        }
        ActivityEasemobGroupInfoDTO activityEasemobGroupInfoDTO=new ActivityEasemobGroupInfoDTO();
        activityEasemobGroupInfoDTO.setActivityID(activityDao.getActivityByGroupID(activityEasemobGroup.getId().toString()).getId().toString());
        activityEasemobGroupInfoDTO.setActivityEasemobGroupUserDTO(list);
        return activityEasemobGroupInfoDTO;
    }

    @Override
    public void sendReservationMessage() {
        List<Activity> list=activityDao.getReadyActivity();
        String content=remindTemplateDao.getRemindTemplateEntityByID("1").getContent();
        for (Activity a: list) {
            String message=content.replace("{活动名称}",a.getTitle());
            RemindEntity remindEntity=new RemindEntity();
            remindEntity.setContent(message);
            remindEntity.setIcon("");
            remindEntity.setCreate_by("");
            remindEntity.setRemind_temlate_id("1");
            remindEntity.setTitle(a.getTitle()+"开始提醒");
            remindEntity.setUri("");
            remindEntity.setRemark("");
            remindEntity.setType("1");
            remindEntity.setType_id(a.getId().toString());
            remindDao.addRemind(remindEntity);
            List<ActivityUser> list1=activityUserDao.getActivityUserList(a.getId().toString());
            String users="";
            for (int i=0;i<list1.size();i++) {
                ActivityUser au=list1.get(i);
                SysElderUserDTO sysElderUserDTO=sysElderUserDao.getSysElderUser(au.getSysElderUserID());
                if(i==0) {
                    users += "\"" + sysElderUserDTO.getEasemobID() + "\"";
                }else{
                    users += ",\"" + sysElderUserDTO.getEasemobID() + "\"";
                }
                RemindUserEntity remindUserEntity=new RemindUserEntity();
                remindUserEntity.setRemind_id(remindEntity.getId());
                remindUserEntity.setStatus("0");
                remindUserEntity.setSys_elder_user_id(sysElderUserDTO.getId());
                remindUserDao.addRemindUser(remindUserEntity);
            }
            EasemobService.sendEasemobMessage(users,message,"users");
        }
    }

    @Override
    public void insertIP(String ip) {
        activityDao.insertIP(ip);
    }

    @Override
    public Page activityListByBackEnd(String hospitalID,String status,Integer pageNo,Integer pageSize,String searchValue,String startDate,String endDate) {
        Page page=new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return activityDao.getActivityListByBackEnd(hospitalID,status,searchValue,startDate,endDate,page);
    }

    @Override
    public List<ActivityUser> activityUserListByBackEnd(String activityID) {
        return activityUserDao.getActivityUserList(activityID);
    }

    @Override
    public Integer delActivity(String id) {
        return activityDao.delActivity(id);
    }

    @Override
    public List<SysHospitalUserDTO> getAllHospital() {
        return sysHospitalUserDao.getAllHospital();
    }

    @Override
    public Page getAllActivityListByBackEnd(ActivityDTO activityDTO, Page page) {
        return activityDao.getAllActivityListByBackEnd(activityDTO,page);
    }

}
