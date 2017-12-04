package com.yhl.laoyou.modules.myService.service;

import com.yhl.laoyou.common.dto.activity.ActivityDTO;
import com.yhl.laoyou.common.dto.activity.ActivityDiscussDTO;
import com.yhl.laoyou.common.dto.activity.ActivityEasemobGroupInfo;
import com.yhl.laoyou.common.dto.activity.AttendedActivityDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.activityService.DTO.ActivityEasemobGroupInfoDTO;
import com.yhl.laoyou.modules.activityService.DTO.ActivityEasemobGroupUserDTO;
import com.yhl.laoyou.modules.activityService.entity.*;
import com.yhl.laoyou.modules.sys.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zbm84 on 2017/7/24.
 */
@Service
public interface ActivityService {

    ActivityDTO getActivity(String id);

    List<ActivityDTO> getActivityList(String elderID,String pageNo,String activityType);

    Integer addActivity(Activity activity,User user);

    List<AttendedActivityDTO> getActivityByElderUser(String elderID);

    List<ActivityDiscussDTO> getActivityDiscuss(String id,Integer page);

    Integer addActivityDiscuss(ActivityDiscussDTO activityDiscussDTO);

    Integer updateActivtyStatus();

    Integer getActivityAttendStatus(String activityID,String elderID);

    String addActivityUser(String activityID,List<String> elderID);

    void createActivityEasemobGroup(String activityID,User user);

    void joinActivityEasemobGroup(String activityEasemobGroupID, User user);

    List<ActivityEasemobGroupInfo> getActivityEasemobGroupUserList(String activityID);

    List<ActivityDTO> getActivityListBySearch(String search);

    void addActivityFavorite(String activityID,String sysElderUserID);

    void delActivityFavorite(String activityID,String sysElderUserID);

    List<ActivityDTO> activityListByFirstPage(String hospitalID);

    List<ActivityDTO> getMyFavoriteActivityList(String sysElderUserID);

    List<ActivityEasemobGroup> getUserActivityGroupInfo(String elderEasemobID);

    List<ActivityEasemobGroup> getUserActivityEasemobGroupList(String elderEasemobID);

    ActivityEasemobGroupInfoDTO getActivityEasemobGroupUsers(String groupID);

    void sendReservationMessage();

    void insertIP(String ip);

    Page activityListByBackEnd(String hospitalID, String status, Integer pageNo, Integer pageSize, String searchValue, String startDate, String endDate);

    List<ActivityUser> activityUserListByBackEnd(String activityUser);

    Integer delActivity(String id);

    List<SysHospitalUserDTO> getAllHospital();

    Page getAllActivityListByBackEnd(ActivityDTO activityDTO,Page page);
}
