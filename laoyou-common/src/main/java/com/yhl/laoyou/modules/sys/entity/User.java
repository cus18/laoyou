/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.entity;

import java.util.Date;

import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysPractitionerUserDTO;
import com.yhl.laoyou.common.persistence.DataEntity;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;

	private String loginName;    // 登录名
	private String password;     // 密码
	private String gender;       // 性别
	private String name;	     // 姓名
	private String age;			 //年龄
	private String email;	     // 邮箱
	private String phone;	     // 电话
	private String mobile;	     // 手机
	private String userType;     // 用户类型
	private String photo;        // 头像;
	private String Area;  	     // 地区
	private String address;		 // 地址
	private String loginIp;	     // 最后登陆IP
	private Date loginDate;	     // 最后登陆日期
	private String loginFlag;	 // 是否允许登陆
	private char del_flag;

	private SysPractitionerUserDTO sysPractitionerUser;

	private SysElderUserDTO sysElderUserDTO;

	private SysHospitalUserDTO sysHospitalUserDTO;

	//存储用户登录渠道，判断是否需要强制下线。
	private String source;

	public User(String id) {
	}

	public User() {

	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	@Override
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public char getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(char del_flag) {
		this.del_flag = del_flag;
	}


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public SysPractitionerUserDTO getSysPractitionerUser() {
		return sysPractitionerUser;
	}

	public void setSysPractitionerUser(SysPractitionerUserDTO sysPractitionerUser) {
		this.sysPractitionerUser = sysPractitionerUser;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SysElderUserDTO getSysElderUserDTO() {
		return sysElderUserDTO;
	}

	public void setSysElderUserDTO(SysElderUserDTO sysElderUserDTO) {
		this.sysElderUserDTO = sysElderUserDTO;
	}

	public SysHospitalUserDTO getSysHospitalUserDTO() {
		return sysHospitalUserDTO;
	}

	public void setSysHospitalUserDTO(SysHospitalUserDTO sysHospitalUserDTO) {
		this.sysHospitalUserDTO = sysHospitalUserDTO;
	}
}