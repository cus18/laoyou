package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.EmptyNestElderBaseRogerInfo;
import com.yhl.laoyou.common.dto.QuestionnaireInfoDTO;
import com.yhl.laoyou.common.dto.QuestionnaireInfoRogerDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.EmptyNestElderBaseInfo;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.common.utils.excel.ImportExcel;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.dao.EmptyNestElderBaseInfoDao;
import com.yhl.laoyou.modules.sys.dao.QuestionnaireInfoDao;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于账户管理
 *
 * @author 张博
 * @date 2017-5-5
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    private static Lock lock = new ReentrantLock();

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private QuestionnaireInfoDao questionnaireInfoDao;

    @Autowired
    EmptyNestElderBaseInfoDao emptyNestElderBaseInfoDao;

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/uploadMemberFile", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO uploadMemberFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            System.out.println("fileName：" + file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("/")
                    + "/uploadTempDirectory/" + new Date().getTime() + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            ImportExcel ie = new ImportExcel(newFile, 1);
            List<QuestionnaireInfoDTO> list = ie.getDataList(QuestionnaireInfoDTO.class);
            for (int i = 0; i < list.size(); i++) {
                QuestionnaireInfoDTO q = list.get(i);
                try {
                    questionnaireInfoDao.insert(q);
                    System.out.println("调查问卷已录入=====" + q.getName() + "====" + q.getAge());
                } catch (Exception e) {
                    System.err.println("调查问卷录入失败@@@@@@" + q.getName() + "@@@@@@" + q.getAge());
                    continue;
                }
            }
            ImportExcel ies = new ImportExcel(newFile, 1, 1);
            List<EmptyNestElderBaseInfo> lists = ies.getDataList(EmptyNestElderBaseInfo.class);
            for (EmptyNestElderBaseInfo vo : lists) {
                DecimalFormat df = new DecimalFormat("0");
                if (StringUtils.isNotNull(vo.getPhone())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getPhone()));
                    } catch (ParseException e) {
                        ppp = vo.getPhone();
                    }
                    vo.setPhone(ppp);
                }
                if (StringUtils.isNotNull(vo.getCommunityPhone())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getCommunityPhone()));
                    } catch (ParseException e) {
                        ppp = vo.getCommunityPhone();
                    }
                    vo.setCommunityPhone(ppp);
                }
                if (StringUtils.isNotNull(vo.getMobile())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getMobile()));
                    } catch (ParseException e) {
                        ppp = vo.getMobile();
                    }
                    vo.setMobile(ppp);
                }
                if (StringUtils.isNotNull(vo.getPartyMemberVolunteersPhone())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getPartyMemberVolunteersPhone()));
                    } catch (ParseException e) {
                        ppp = vo.getPartyMemberVolunteersPhone();
                    }
                    vo.setPartyMemberVolunteersPhone(ppp);
                }
                if (StringUtils.isNotNull(vo.getRelativesPhone1())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getRelativesPhone1()));
                    } catch (ParseException e) {
                        ppp = vo.getRelativesPhone1();
                    }
                    vo.setRelativesPhone1(ppp);
                }
                if (StringUtils.isNotNull(vo.getRelativesPhone2())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getRelativesPhone2()));
                    } catch (ParseException e) {
                        ppp = vo.getRelativesPhone2();
                    }
                    vo.setRelativesPhone2(ppp);
                }
                if (StringUtils.isNotNull(vo.getVillageCadresPhone())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getVillageCadresPhone()));
                    } catch (ParseException e) {
                        ppp = vo.getVillageCadresPhone();
                    }
                    vo.setVillageCadresPhone(ppp);
                }
                try {
                    emptyNestElderBaseInfoDao.importEmptyElderBaseInfo(vo);
                    System.out.println("基本信息已录入=========" + vo.getName() + "=====" + vo.getAge());
                } catch (Exception e) {
                    if (vo.getAge().equals("")) {
                        vo.setAge("1");
                    }
                    try {
                        emptyNestElderBaseInfoDao.importEmptyElderBaseInfo(vo);
                    } catch (Exception e1) {
                        System.err.println("基本信息录入失败@@@@@@@@@@@" + vo.getName() + "@@@@@@@@@" + vo.getAge() + "---------" + e.getMessage());
                        continue;
                    }
                }
            }
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setResult(StatusConstant.FAILURE);
            responseDTO.setErrorInfo("会员信息不完整");
            return responseDTO;
        }
    }

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/uploadEmptyNestElderBaseInfoFile", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO uploadEmptyNestElderBaseInfoFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            System.out.println("fileName：" + file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("/")
                    + "/uploadTempDirectory/" + new Date().getTime() + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            ImportExcel ie = new ImportExcel(newFile, 1, 1);
            List<EmptyNestElderBaseInfo> list = ie.getDataList(EmptyNestElderBaseInfo.class);
            for (EmptyNestElderBaseInfo vo : list) {
                DecimalFormat df = new DecimalFormat("0");
                if (StringUtils.isNotNull(vo.getPhone())) {
                    vo.setPhone(df.format(df.parse(vo.getPhone())));
                }
                if (StringUtils.isNotNull(vo.getCommunityPhone())) {
                    vo.setCommunityPhone(df.format(df.parse(vo.getCommunityPhone())));
                }
                if (StringUtils.isNotNull(vo.getMobile())) {
                    vo.setMobile(df.format(df.parse(vo.getMobile())));
                }
                if (StringUtils.isNotNull(vo.getPartyMemberVolunteersPhone())) {
                    vo.setPartyMemberVolunteersPhone(df.format(df.parse(vo.getPartyMemberVolunteersPhone())));
                }
                if (StringUtils.isNotNull(vo.getRelativesPhone1())) {
                    String ppp = "";
                    try {
                        ppp = df.format(df.parse(vo.getRelativesPhone1()));
                    } catch (ParseException e) {
                        ppp = vo.getRelativesPhone1();
                    }
                    vo.setRelativesPhone1(ppp);
                }
                if (StringUtils.isNotNull(vo.getRelativesPhone2())) {
                    vo.setRelativesPhone2(df.format(df.parse(vo.getRelativesPhone2())));
                }
                if (StringUtils.isNotNull(vo.getVillageCadresPhone())) {
                    vo.setVillageCadresPhone(df.format(df.parse(vo.getVillageCadresPhone())));
                }
                if (StringUtils.isNotNull(vo.getName()) && StringUtils.isNotNull(vo.getAge()) && StringUtils.isNotNull(vo.getIdCard())) {
                    emptyNestElderBaseInfoDao.importEmptyElderBaseInfo(vo);
                    mongoTemplate.insert(vo);
                }
//                System.out.println("=================="+vo.getName()+"============================");
            }
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setResult(StatusConstant.FAILURE);
            responseDTO.setErrorInfo("会员信息不完整");
            return responseDTO;
        }
    }

    /**
     * 上传emptyInfo信息;
     */
    @RequestMapping(value = "/uploadBasicInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    HashMap<String,Object> uploadBasicInfo(@RequestBody HashMap<String,Object> params) {
        HashMap<String,Object> result = new HashMap<String,Object>();
        synchronized (this){
            try {
                EmptyNestElderBaseInfo vo = new EmptyNestElderBaseInfo();
                vo.setInputerName(String.valueOf(params.get("inputerName")));
                vo.setStreet(String.valueOf(params.get("street")));
                vo.setCommunity(String.valueOf(params.get("community")));
                vo.setIdCard(String.valueOf(params.get("idCard")));
                vo.setAge(String.valueOf(params.get("age")));
                vo.setName(String.valueOf(params.get("name")));
                vo.setGender(String.valueOf(params.get("gendar")));
                vo.setMobile(String.valueOf(params.get("mobile")));
                vo.setPhone(String.valueOf(params.get("phone")));
                vo.setNation(String.valueOf(params.get("nation")));
                vo.setOrigin(String.valueOf(params.get("origin")));
                vo.setPolitical(String.valueOf(params.get("political")));
                vo.setCultural(String.valueOf(params.get("cultural")));
                vo.setMarital(String.valueOf(params.get("marital")));
                vo.setIdentity(String.valueOf(params.get("identity")));
                vo.setHouseholdType(String.valueOf(params.get("householdType")));
                vo.setCensusStatus(String.valueOf(params.get("censusStatus")));
                vo.setLivingConditions(String.valueOf(params.get("livingConditions")));
                vo.setPermanentArea(String.valueOf(params.get("permanentArea")));
                vo.setPermanentAddress(String.valueOf(params.get("permanentAddress")));
                vo.setLivingAddress(String.valueOf(params.get("livingAddress")));
                vo.setCommunityPhone(String.valueOf(params.get("communityPhone")));
                vo.setDisability(String.valueOf(params.get("disability")));
                vo.setDisabilityCard(String.valueOf(params.get("disabilityCard")));
                vo.setRelativesName1(String.valueOf(params.get("relativesName1")));
                vo.setRelativesRelationship1(String.valueOf(params.get("relativeRelationship1")));
                vo.setRelativesPhone1(String.valueOf(params.get("relativesPhone1")));
                vo.setRelativesArea1(String.valueOf(params.get("relativesArea1")));
                vo.setRelativesAddress1(String.valueOf(params.get("relativesAddress1")));
                vo.setRelativesName2(String.valueOf(params.get("relativesName2")));
                vo.setRelativesRelationship2(String.valueOf(params.get("relativeRelationship2")));
                vo.setRelativesPhone2(String.valueOf(params.get("relativesPhone2")));
                vo.setRelativesArea2(String.valueOf(params.get("relativesArea2")));
                vo.setRelativesAddress2(String.valueOf(params.get("relativesAddress2")));
                vo.setVillageCadres(String.valueOf(params.get("villageCadres")));
                vo.setVillageCadresPhone(String.valueOf(params.get("villageCadresPhone")));
                vo.setVillageCadresAddress(String.valueOf(params.get("villageCadresAddress")));
                vo.setPartyMemberVolunteers(String.valueOf(params.get("partyMemberVolunteers")));
                vo.setPartyMemberVolunteersPhone(String.valueOf(params.get("partMemberVolunteersPhone")));
                vo.setPartyMemberVolunteersAddress(String.valueOf(params.get("partMemberVolunteersAddress")));
                vo.setRemark(String.valueOf(params.get("remark")));
                vo.setFormHolder(String.valueOf(params.get("formHolder")));
                vo.setAuditor(String.valueOf(params.get("auditor")));

                emptyNestElderBaseInfoDao.importEmptyElderBaseInfo(vo);
                mongoTemplate.insert(vo);
                result.put("result","success");
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.put("result","failure");
                return result;
            }
        }
    }


    /**
     * 上传serviceInfo信息;
     */
    @RequestMapping(value = "/uploadServiceInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    HashMap<String,Object> uploadServiceInfo(@RequestBody HashMap<String,Object> params) {
        HashMap<String,Object> result = new HashMap<String,Object>();
        synchronized (this) {
            try {
                QuestionnaireInfoDTO vo = new QuestionnaireInfoDTO();
                vo.setInputerName(String.valueOf(params.get("inputerName")));
                vo.setStreet(String.valueOf(params.get("street")));
                vo.setName(String.valueOf(params.get("name")));
                vo.setAge(String.valueOf(params.get("age")));
                vo.setIdCard(String.valueOf(params.get("idCard")));
                vo.setLastJob(String.valueOf(params.get("lastJob")));
                vo.setLastJobE(String.valueOf(params.get("lastJobE")));
                vo.setMoneySourceA(String.valueOf(params.get("moneySourceA")));
                vo.setMoneySourceB(String.valueOf(params.get("moneySourceB")));
                vo.setMoneySourceC(String.valueOf(params.get("moneySourceC")));
                vo.setIfPartyMembers(String.valueOf(params.get("ifPartyMembers")));
                vo.setNeedPartyServices(String.valueOf(params.get("needPartyServices")));
                vo.setNeedPartyServicesE(String.valueOf(params.get("needPartyServicesE")));
                vo.setSickness(String.valueOf(params.get("sickness")));
                vo.setSicknessF(String.valueOf(params.get("sicknessF")));
                vo.setNeedFirstAidServices(String.valueOf(params.get("needFirstAidServices")));
                vo.setNeedHealthServices(String.valueOf(params.get("needHealthServices")));
                vo.setNeedMedicalServices(String.valueOf(params.get("needMedicalServices")));
                vo.setWhatPension(String.valueOf(params.get("whatPension")));
                vo.setWhatPensionE(String.valueOf(params.get("whatPensionE")));
                vo.setNeedLifeServices(String.valueOf(params.get("needLifeServices")));
                vo.setNeedEmergencyServices(String.valueOf(params.get("needEmergencyServices")));
                vo.setNeedGPSServices(String.valueOf(params.get("needGPSServices")));
                vo.setNeedPhoneServices(String.valueOf(params.get("needPhoneServices")));
                vo.setWhatNeedPhoneServices(String.valueOf(params.get("whatNeedPhoneServices")));
                vo.setInvestigator(String.valueOf(params.get("investigator")));

                questionnaireInfoDao.insert(vo);
                this.mongoTemplate.insert(vo, "QuestionnaireInfo");
                result.put("result","success");
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.put("result","failure");
                return result;
            }
        }
    }

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/roger/uploadMemberFile", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO uploadMemberFile1() throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        ResponseDTO responseDTO = new ResponseDTO();

            File newFile = new File("/project/roger/7.xlsx");
            ImportExcel ie = new ImportExcel(newFile, 1);
            List<QuestionnaireInfoRogerDTO> list = ie.getDataList(QuestionnaireInfoRogerDTO.class);
            for(QuestionnaireInfoRogerDTO questionnaireInfoRogerDTO:list)
            {
                try {

                QuestionnaireInfoDTO questionnaireInfoDTO = new QuestionnaireInfoDTO();

                int idNum = Integer.parseInt(questionnaireInfoRogerDTO.getIdNum());
                if(idNum>=20001&&idNum<=21933)
                {
                    questionnaireInfoDTO.setStreet("云冈");
                }
                else if(idNum>=30001&&idNum<=31089)
                {
                    questionnaireInfoDTO.setStreet("和义");
                }
                else if(idNum>=31101&&idNum<=32233)
                {
                    questionnaireInfoDTO.setStreet("方庄");
                }
                else if(idNum>=22001&&idNum<=22061)
                {
                    questionnaireInfoDTO.setStreet("东铁营");
                }
                else if(idNum>=22062&&idNum<=22112)
                {
                    questionnaireInfoDTO.setStreet("东铁营");
                }
                else if(idNum>=22113&&idNum<=22269)
                {
                    questionnaireInfoDTO.setStreet("东铁营");
                }
                else if(idNum>=32251&&idNum<=33673)
                {
                    questionnaireInfoDTO.setStreet("东铁营");
                }

                questionnaireInfoDTO.setInputerName("roger");
                questionnaireInfoDTO.setName(questionnaireInfoRogerDTO.getName());
                questionnaireInfoDTO.setAge(questionnaireInfoRogerDTO.getAge());
                questionnaireInfoDTO.setIdCard(questionnaireInfoRogerDTO.getIdCard());


                if(questionnaireInfoRogerDTO.getLastJob().equals("e"))
                {
                    questionnaireInfoDTO.setLastJob("");
                    if(questionnaireInfoRogerDTO.getLastJobE().equals(""))
                    {
                        questionnaireInfoDTO.setLastJobE("1");
                    }
                    else
                    {
                        questionnaireInfoDTO.setLastJobE(questionnaireInfoRogerDTO.getLastJobE());
                    }

                }
                else
                {
                    questionnaireInfoDTO.setLastJob(questionnaireInfoRogerDTO.getLastJob());
                    questionnaireInfoDTO.setLastJobE(questionnaireInfoRogerDTO.getLastJobE());
                }

                questionnaireInfoDTO.setMoneySourceA(questionnaireInfoRogerDTO.getMoneySourceA());
                questionnaireInfoDTO.setMoneySourceB(questionnaireInfoRogerDTO.getMoneySourceB());
                questionnaireInfoDTO.setMoneySourceC(questionnaireInfoRogerDTO.getMoneySourceC());
                questionnaireInfoDTO.setIfPartyMembers(questionnaireInfoRogerDTO.getIfPartyMembers());


                if(questionnaireInfoRogerDTO.getNeedPartyServices().contains("e"))
                {
                    questionnaireInfoDTO.setNeedPartyServices(questionnaireInfoRogerDTO.getNeedPartyServices().replace("e",""));
                    if(questionnaireInfoRogerDTO.getNeedPartyServicesE().equals(""))
                    {
                        questionnaireInfoDTO.setNeedPartyServicesE("1");
                    }
                    else
                    {
                        questionnaireInfoDTO.setNeedPartyServicesE(questionnaireInfoRogerDTO.getNeedPartyServicesE());
                    }

                }
                else
                {
                    questionnaireInfoDTO.setNeedPartyServices(questionnaireInfoRogerDTO.getNeedPartyServices());
                    questionnaireInfoDTO.setNeedPartyServicesE(questionnaireInfoRogerDTO.getNeedPartyServicesE());
                }

                if(questionnaireInfoRogerDTO.getSickness().contains("f"))
                {
                    questionnaireInfoDTO.setSickness(questionnaireInfoRogerDTO.getSickness().replace("f",""));
                    if(questionnaireInfoRogerDTO.getSicknessF().equals(""))
                    {
                        questionnaireInfoDTO.setSicknessF("1");
                    }
                    else
                    {
                        questionnaireInfoDTO.setSicknessF(questionnaireInfoRogerDTO.getSicknessF());
                    }

                }
                else
                {
                    questionnaireInfoDTO.setSickness(questionnaireInfoRogerDTO.getSickness());
                    questionnaireInfoDTO.setSicknessF(questionnaireInfoRogerDTO.getSicknessF());
                }


                questionnaireInfoDTO.setNeedFirstAidServices(questionnaireInfoRogerDTO.getNeedFirstAidServices());
                questionnaireInfoDTO.setNeedHealthServices(questionnaireInfoRogerDTO.getNeedHealthServices());
                questionnaireInfoDTO.setNeedMedicalServices(questionnaireInfoRogerDTO.getNeedMedicalServices());

                if(questionnaireInfoRogerDTO.getWhatPension().contains("e"))
                {
                    questionnaireInfoDTO.setWhatPension(questionnaireInfoRogerDTO.getWhatPension().replace("e",""));
                    if(questionnaireInfoRogerDTO.getWhatPensionE().equals(""))
                    {
                        questionnaireInfoDTO.setWhatPensionE("1");
                    }
                    else
                    {
                        questionnaireInfoDTO.setWhatPensionE(questionnaireInfoRogerDTO.getWhatPensionE());
                    }

                }
                else
                {
                    questionnaireInfoDTO.setWhatPension(questionnaireInfoRogerDTO.getWhatPension());
                    questionnaireInfoDTO.setWhatPensionE(questionnaireInfoRogerDTO.getWhatPensionE());
                }


                questionnaireInfoDTO.setNeedLifeServices(questionnaireInfoRogerDTO.getNeedLifeServices());
                questionnaireInfoDTO.setNeedEmergencyServices(questionnaireInfoRogerDTO.getNeedEmergencyServices());
                questionnaireInfoDTO.setNeedGPSServices(questionnaireInfoRogerDTO.getNeedGPSServices());
                questionnaireInfoDTO.setNeedPhoneServices(questionnaireInfoRogerDTO.getNeedPhoneServices());
                questionnaireInfoDTO.setWhatNeedPhoneServices(questionnaireInfoRogerDTO.getWhatNeedPhoneServices());
                questionnaireInfoDTO.setInvestigator(questionnaireInfoRogerDTO.getInvestigator());


                questionnaireInfoDao.insert(questionnaireInfoDTO);
                this.mongoTemplate.insert(questionnaireInfoDTO, "QuestionnaireInfo");


                System.out.println("=================="+questionnaireInfoDTO.getName()+"============================");
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }

            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
    }

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/cjk/uploadMemberFile", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ResponseDTO uploadMemberFile3() throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        ResponseDTO responseDTO = new ResponseDTO();

            File newFile = new File("/project/roger/14.xlsx");
            ImportExcel ie = new ImportExcel(newFile, 1);
            List<QuestionnaireInfoDTO> list = ie.getDataList(QuestionnaireInfoDTO.class);
            for (int i = 0; i < list.size(); i++) {
                try {
                QuestionnaireInfoDTO q=list.get(i);
                if (q.getName() == null || q.getName().equals("")) {
                    continue;
                }
                questionnaireInfoDao.insert(q);
                this.mongoTemplate.insert(q, "QuestionnaireInfo");

                System.out.println("=================="+q.getName()+"============================");
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

            }
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;

    }

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/roger/uploadEmptyNestElderBaseInfoFile", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ResponseDTO uploadEmptyNestElderBaseInfoFile1() throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        ResponseDTO responseDTO = new ResponseDTO();

            File newFile = new File("/project/roger/13.xlsx");
            ImportExcel ie = new ImportExcel(newFile, 1);
            List<EmptyNestElderBaseRogerInfo> list = ie.getDataList(EmptyNestElderBaseRogerInfo.class);
            for(EmptyNestElderBaseRogerInfo vo : list){

                try {

                EmptyNestElderBaseInfo emptyNestElderBaseInfo = new EmptyNestElderBaseInfo();

                emptyNestElderBaseInfo.setInputerName("roger");
                emptyNestElderBaseInfo.setStreet(vo.getStreet());
                emptyNestElderBaseInfo.setCommunity(vo.getCommunity());
                emptyNestElderBaseInfo.setIdCard(vo.getIdCard());

                if(vo.getName().equals("")){
                    continue;
                }

                if(vo.getAge().equals(""))
                {
                    emptyNestElderBaseInfo.setAge("1");
                }
                else
                {
                    emptyNestElderBaseInfo.setAge(vo.getAge());
                }

                emptyNestElderBaseInfo.setName(vo.getName());
                emptyNestElderBaseInfo.setGender(vo.getGender());
                emptyNestElderBaseInfo.setMobile(vo.getMobile());
                emptyNestElderBaseInfo.setPhone(vo.getPhone());
                emptyNestElderBaseInfo.setNation(vo.getNation());
                emptyNestElderBaseInfo.setOrigin(vo.getOrigin());
                emptyNestElderBaseInfo.setPolitical(vo.getPolitical());
                emptyNestElderBaseInfo.setCultural(vo.getCultural());
                emptyNestElderBaseInfo.setMarital(vo.getMarital());
                emptyNestElderBaseInfo.setIdentity(vo.getIdentity());
                emptyNestElderBaseInfo.setHouseholdType(vo.getHouseholdType());
                emptyNestElderBaseInfo.setCensusStatus(vo.getCensusStatus());
                emptyNestElderBaseInfo.setLivingConditions(vo.getLivingConditions());
                emptyNestElderBaseInfo.setPermanentArea(vo.getPermanentArea());
                emptyNestElderBaseInfo.setPermanentAddress(vo.getPermanentAddress());
                emptyNestElderBaseInfo.setLivingAddress(vo.getLivingAddress());
                emptyNestElderBaseInfo.setCommunityPhone(vo.getCommunityPhone());
                emptyNestElderBaseInfo.setDisability(vo.getDisability());
                emptyNestElderBaseInfo.setDisabilityCard(vo.getDisabilityCard());
                emptyNestElderBaseInfo.setRelativesName1(vo.getRelativesName1());
                emptyNestElderBaseInfo.setRelativesRelationship1(vo.getRelativesRelationship1());
                emptyNestElderBaseInfo.setRelativesPhone1(vo.getRelativesPhone1());
                emptyNestElderBaseInfo.setRelativesArea1(vo.getRelativesArea1());
                emptyNestElderBaseInfo.setRelativesAddress1(vo.getRelativesAddress1());
                emptyNestElderBaseInfo.setRelativesName2(vo.getRelativesName2());
                emptyNestElderBaseInfo.setRelativesRelationship2(vo.getRelativesRelationship2());
                emptyNestElderBaseInfo.setRelativesPhone2(vo.getRelativesPhone2());
                emptyNestElderBaseInfo.setRelativesArea2(vo.getRelativesArea2());
                emptyNestElderBaseInfo.setRelativesAddress2(vo.getRelativesAddress2());
                emptyNestElderBaseInfo.setVillageCadres(vo.getVillageCadres());
                emptyNestElderBaseInfo.setVillageCadresPhone(vo.getVillageCadresPhone());
                emptyNestElderBaseInfo.setVillageCadresAddress(vo.getVillageCadresAddress());
                emptyNestElderBaseInfo.setPartyMemberVolunteers(vo.getPartyMemberVolunteers());
                emptyNestElderBaseInfo.setPartyMemberVolunteersPhone(vo.getPartyMemberVolunteersPhone());
                emptyNestElderBaseInfo.setPartyMemberVolunteersAddress(vo.getPartyMemberVolunteersAddress());
                emptyNestElderBaseInfo.setRemark(vo.getRemark());
                emptyNestElderBaseInfo.setFormHolder(vo.getFormHolder());
                emptyNestElderBaseInfo.setAuditor(vo.getAuditor());

                emptyNestElderBaseInfoDao.importEmptyElderBaseInfo(emptyNestElderBaseInfo);
                mongoTemplate.insert(emptyNestElderBaseInfo);
                System.out.println("=================="+vo.getName()+"============================");
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
    }


}
