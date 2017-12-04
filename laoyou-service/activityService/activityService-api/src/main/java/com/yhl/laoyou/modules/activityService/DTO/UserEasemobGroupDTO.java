package com.yhl.laoyou.modules.activityService.DTO;

import com.yhl.laoyou.common.dto.activity.ActivityEasemobGroupInfo;
import com.yhl.laoyou.modules.activityService.entity.ActivityEasemobGroup;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/16.
 */
public class UserEasemobGroupDTO {

    private EasemobGroup easemobGroup;
    private List<ActivityEasemobGroup> activityEasemobGroupInfoList;

    public EasemobGroup getEasemobGroup() {
        return easemobGroup;
    }

    public void setEasemobGroup(EasemobGroup easemobGroup) {
        this.easemobGroup = easemobGroup;
    }

    public List<ActivityEasemobGroup> getActivityEasemobGroupInfoList() {
        return activityEasemobGroupInfoList;
    }

    public void setActivityEasemobGroupInfoList(List<ActivityEasemobGroup> activityEasemobGroupInfoList) {
        this.activityEasemobGroupInfoList = activityEasemobGroupInfoList;
    }
}
