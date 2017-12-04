package com.yhl.laoyou.modules.healthDataService.service.impl;

import com.aliyun.opensearch.sdk.generated.document.Command;
import com.aliyun.opensearch.sdk.generated.search.Order;
import com.aliyun.opensearch.sdk.generated.search.Sort;
import com.aliyun.opensearch.sdk.generated.search.SortField;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yhl.laoyou.common.constant.OperationType;
import com.yhl.laoyou.common.dto.hospital.DeviceDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.OpenSearchTool;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.healthDataService.dao.DeviceDao;
import com.yhl.laoyou.modules.healthDataService.service.DeviceService;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by sunxiao on 2017/6/20.
 */
@Service
@Transactional(readOnly = false)
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void operDevice(DeviceDTO deviceDTO) throws Exception{
        LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
        if(StringUtils.isNotNull(deviceDTO.getMemberId())){
            SysElderUserDTO dto = new SysElderUserDTO();
            dto.setMemberCardID(deviceDTO.getMemberId());
            List<User> list = userDao.getUserByInfo(dto);
            if(list!=null&&list.size()>0){
                deviceDTO.setUserName(list.get(0).getName());
            }else{
                throw new Exception("没有找到该会员号的用户！");
            }
        }
        String oper = deviceDTO.getOper();
        if(OperationType.ADD.getValue().equals(oper)){
            deviceDao.addDevice(deviceDTO);
            oper = Command.ADD.toString();
        } else if(OperationType.UPDATE.getValue().equals(oper)){
            synchronized (this){
                deviceDao.updateDevice(deviceDTO);
            }
            oper = Command.UPDATE.toString();
        } else if(OperationType.DELETE.getValue().equals(oper)){
            deviceDao.delDevice(deviceDTO.getId());
            oper = Command.DELETE.toString();
        } else {
            throw new Exception("没有操作类型！") ;
        }


        map.put("id",deviceDTO.getId());
        map.put("name",deviceDTO.getName());
        map.put("type",deviceDTO.getType());
        map.put("deviceid",deviceDTO.getDeviceId());
        map.put("memberid",deviceDTO.getMemberId());
        map.put("username",deviceDTO.getUserName());
        map.put("remarks",deviceDTO.getRemarks());
        String returnStr = OpenSearchTool.pushFile("device", map,"device", oper);
        if("fail".equals(returnStr)){
            throw new Exception("上传opensearch服务器失败！") ;
        }
    }

    @Override
    public void findDeviceList(String param, Page page) throws Exception{
        Sort sorter = new Sort();
        sorter.addToSortFields(new SortField("id", Order.DECREASE)); //设置id字段降序
        ArrayList files = Lists.newArrayList("id", "name","type","deviceid","remarks","memberid","username");
        String result = OpenSearchTool.search(page.getPageNo(),page.getPageSize(),"device",files,param ,sorter);
        JSONObject jsonObject = JSONObject.fromObject(result);
        JSONArray array = jsonObject.getJSONArray("items");
        List<DeviceDTO> list = new ArrayList<>();
        for(int i =0 ;i<array.size();i++){
            JSONObject jo = (JSONObject) array.get(i);
            DeviceDTO dd = new DeviceDTO();
            dd.setId(jo.getInt("id"));
            dd.setName(jo.getString("name"));
            dd.setDeviceId(jo.getString("deviceid"));
            dd.setType(jo.getString("type"));
            dd.setRemarks(jo.getString("remarks"));
            dd.setUserName(jo.getString("username"));
            dd.setMemberId(jo.getString("memberid"));
            list.add(dd);
        }
        page.setList(list);
        page.setCount(jsonObject.getInt("total"));
    }

    @Override
    public JSONArray getStatisticsDevice() throws Exception{
        try{
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = null;
            List<String> list = deviceDao.selectAllType();
            for(String type:list){
                jsonObject = new JSONObject();
                jsonObject.put("deviceType",type);
                jsonObject.put("deviceNum",deviceDao.getDeviceNum(type));
                jsonObject.put("deviceBindNum",deviceDao.deviceBindNum(type));
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("获取数据异常！");
        }
    }
}
