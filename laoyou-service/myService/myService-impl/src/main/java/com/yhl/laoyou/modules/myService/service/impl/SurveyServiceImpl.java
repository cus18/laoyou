package com.yhl.laoyou.modules.myService.service.impl;

import com.yhl.laoyou.common.dto.survey.*;
import com.yhl.laoyou.common.utils.DateUtils;
import com.yhl.laoyou.modules.myService.dao.SurveyDao;
import com.yhl.laoyou.modules.myService.service.SurveyService;
import com.yhl.laoyou.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zbm84 on 2017/5/11.
 */
@Service
@Transactional(readOnly = false)
public class SurveyServiceImpl implements SurveyService {


    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected SurveyDao surveyDao;

    @Override
    public List<SurveyDTO> getAllSurveyQuestions() {
        Query query = new Query().with(new Sort(Sort.Direction.ASC, "questionId"));
        List<SurveyDTO> surveyDTOList = this.mongoTemplate.find(query, SurveyDTO.class,"surveys");
        return surveyDTOList;
    }

    @Override
    public void createOrUpdateAnswer(AnswerDTO answerDTO, User user) throws Exception {

        String answerValue = answerDTO.getQuestionAnswer();

        answerDTO.setQuestionAnswer("");
        List<AnswerDTO> answerDTOList = surveyDao.findSurveyAnswer(answerDTO);

        answerDTO.setQuestionAnswer(answerValue);
        if(answerDTOList.size()==0)
        {
            answerDTO.setId(UUID.randomUUID().toString());
            answerDTO.setWorkerName(user.getName());
            answerDTO.setWorkerPhone(user.getPhone());
            answerDTO.setUpdateDate(new Date());
            surveyDao.createSurveyAnswer(answerDTO);
        }
        else
        {
            if(answerDTO.getQuestionId()==1)
            {
                throw new Exception("already");
            }
            answerDTO.setId(answerDTOList.get(0).getId());
            answerDTO.setWorkerName(user.getName());
            answerDTO.setWorkerPhone(user.getPhone());
            answerDTO.setUpdateDate(new Date());
            surveyDao.updateSurveyAnswer(answerDTO);
        }

    }

    @Override
    public List<AnswerDTO> findSurveyAnswer(AnswerDTO answerDTO) {

        List<AnswerDTO> answerDTOList =  surveyDao.findSurveyAnswer(answerDTO);
        return  answerDTOList;

    }

    @Override
    public List<SingleStatisticDTO> singleStatistic(SurveyDTO surveyDTO) {
        List<SingleStatisticDTO> list = new ArrayList<>();
        String col = "question" + surveyDTO.getQuestionId();
        System.out.println("==========================="+ DateUtils.DateToStr(new Date()));
        List<QuestionItemDTO> itemlist = surveyDTO.getQuestionData();
        for(QuestionItemDTO itemdto: itemlist){
            Query query = new Query();
            if("question4".equals(col)){
                String[] age = itemdto.getQuestionItem().split("-");
                query.addCriteria(Criteria.where(col).gte(age[0]).andOperator(Criteria.where(col).lte(age[1])));
            }else{
                query.addCriteria(Criteria.where(col).regex(".*?" + itemdto.getQuestionItem() + ".*"));
            }
            long totalCount = mongoTemplate.count(query,"surveyQuestion");
            SingleStatisticDTO dto = new SingleStatisticDTO();
            dto.setSingleStatisticName(itemdto.getQuestionItemName());
            dto.setSingleStatisticNum(String.valueOf(totalCount));
            list.add(dto);
        }
        System.out.println("==========================="+ DateUtils.DateToStr(new Date()));
        return list;
    }

    @Override
    public List<List<List>> crossStatistic(List<SurveyDTO> independentVariableList, List<SurveyDTO> dependentVariableList) {
        ExecutorService threadExecutor = Executors.newFixedThreadPool(30);
        allmap = new HashMap();
        int tablecount = independentVariableList.size();

        for(SurveyDTO dto0 : dependentVariableList){
            String row = "question" +dto0.getQuestionId();
            if(tablecount==1){
                String col = "question" + independentVariableList.get(0).getQuestionId();
                List<QuestionItemDTO>  itemlist = independentVariableList.get(0).getQuestionData();
                for(QuestionItemDTO dto : itemlist){
                    for(QuestionItemDTO itemDTO:dto0.getQuestionData()){//一个表的一行
                        threadExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Map<String,QuestionItemDTO> map = new HashMap();
                                    map.put(col,dto);
                                    atomicQuery(map,row,itemDTO);
                                }catch(Exception e){

                                }
                            }
                        });
                    }
                }
            }else if(tablecount==2){
                String col1 = "question" + independentVariableList.get(0).getQuestionId();
                List<QuestionItemDTO>  itemlist1 = independentVariableList.get(0).getQuestionData();
                String col2 = "question" + independentVariableList.get(1).getQuestionId();
                List<QuestionItemDTO>  itemlist2 = independentVariableList.get(1).getQuestionData();
                for(QuestionItemDTO dto1 : itemlist1){
                    for(QuestionItemDTO dto2 : itemlist2){
                        for(QuestionItemDTO itemDTO:dto0.getQuestionData()){//一个表的一行
                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        Map<String,QuestionItemDTO> map = new LinkedHashMap<>();
                                        map.put(col1,dto1);
                                        map.put(col2,dto2);
                                        atomicQuery(map,row,itemDTO);
                                    }catch(Exception e){

                                    }
                                }
                            });
                        }
                    }
                }
            }
        }

        threadExecutor.shutdown();
        while (true) {
            if (threadExecutor.isTerminated()) {
                break;
            }
        }
        System.out.println("线程执行完毕================================");
        System.out.println(allmap);
        List<List<List>> retlist = new LinkedList<>();
        for(SurveyDTO dto0 : dependentVariableList){
            List<List> tablelist = new LinkedList<>();
            if(tablecount==1){
                for(QuestionItemDTO dto : independentVariableList.get(0).getQuestionData()){
                    List rowlist = new LinkedList();
                    for(QuestionItemDTO itemDTO:dto0.getQuestionData()){//一个表的一行
                        rowlist.add(allmap.get(dto.getQuestionItemName()+itemDTO.getQuestionItemName()));
                    }
                    tablelist.add(rowlist);
                }
            }else if(tablecount==2){
                for(QuestionItemDTO dto1 : independentVariableList.get(0).getQuestionData()){
                    for(QuestionItemDTO dto2 : independentVariableList.get(1).getQuestionData()){
                        List rowlist = new LinkedList();
                        for(QuestionItemDTO itemDTO:dto0.getQuestionData()){//一个表的一行
                            rowlist.add(allmap.get(dto1.getQuestionItemName()+dto2.getQuestionItemName()+itemDTO.getQuestionItemName()));
                        }
                        tablelist.add(rowlist);
                    }
                }
            }
            retlist.add(tablelist);
        }
        return retlist;
    }

    public static Map allmap = new HashMap();

    public void atomicQuery(Map<String,QuestionItemDTO> map,String row,QuestionItemDTO rowItemDTO){
        Query query = new Query().addCriteria(Criteria.where(row).regex(".*?" + rowItemDTO.getQuestionItem() + ".*"));
        StringBuffer sb = new StringBuffer();
        for(String key:map.keySet()){
            if("question4".equals(key)){
                String[] age = map.get(key).getQuestionItem().split("-");
                query.addCriteria(Criteria.where(key).gte(age[0]).andOperator(Criteria.where(key).lte(age[1])));
            }else{
                query.addCriteria(Criteria.where(key).regex(".*?" + map.get(key).getQuestionItem() + ".*"));
            }
            sb.append(map.get(key).getQuestionItemName());
        }
        long cellCount = 0;

        try{
            cellCount = mongoTemplate.count(query, "surveyQuestion");
        }catch (Exception e1){
            System.out.println("mongo连不上了");
            try{
                cellCount = mongoTemplate.count(query, "surveyQuestion");
            }catch (Exception e2){
                System.out.println("mongo连不上了");
                cellCount = mongoTemplate.count(query, "surveyQuestion");
            }
        }
        allmap.put(sb.toString()+rowItemDTO.getQuestionItemName(),(int)cellCount);

    }

    @Override
    public void diyStatistic(List<DiyStatisticRequestDTO> diyStatisticRequestDTOList,List<DiyStatisticResponseDTO> resultList) {
        Map<String,List<DiyStatisticRequestDTO>> map = new HashMap();
        for(DiyStatisticRequestDTO dto:diyStatisticRequestDTOList){
            String key = "question" + dto.getSurveyDTO().getQuestionId();
            List<DiyStatisticRequestDTO> list = new ArrayList();
            if(map.keySet().contains(key)){
                map.get(key).add(dto);
            }else{
                list.add(dto);
                map.put(key,list);
            }
        }

        Query query = new Query();
        DiyStatisticResponseDTO resultDTO = new DiyStatisticResponseDTO();
        for(String key :map.keySet()){
            List<DiyStatisticRequestDTO> list = map.get(key);
            if(list.size()==1){
                DiyStatisticRequestDTO dto = list.get(0);
                if("equal".equals(dto.getCondition())){
                    query.addCriteria(Criteria.where(key).is(dto.getStatisticValue()));
                }else if("notEqual".equals(dto.getCondition())){
                    query.addCriteria(Criteria.where(key).ne(dto.getStatisticValue()));
                }else if("larger".equals(dto.getCondition())){
                    query.addCriteria(Criteria.where(key).gt(dto.getStatisticValue()));
                }else if("smaller".equals(dto.getCondition())){
                    query.addCriteria(Criteria.where(key).lt(dto.getStatisticValue()));
                }else if("input".equals(dto.getCondition())){
                    query.addCriteria(Criteria.where(key).regex(".*?" + dto.getStatisticValue() + ".*"));
                }
            }else{
                Criteria criteria = new Criteria();
                List<Criteria> andOpers = new ArrayList<>();
                List<Criteria> orOpers = new ArrayList<>();
                for(DiyStatisticRequestDTO dto: map.get(key)){
                    if("equal".equals(dto.getCondition())){
                        Criteria equal = new Criteria().where(key).is(dto.getStatisticValue());
                        orOpers.add(equal);
                    }else if("notEqual".equals(dto.getCondition())){
                        Criteria notEqual = new Criteria().where(key).ne(dto.getStatisticValue());
                        andOpers.add(notEqual);
                    }else if("larger".equals(dto.getCondition())){
                        Criteria larger = new Criteria().where(key).gt(dto.getStatisticValue());
                        andOpers.add(larger);
                    }else if("smaller".equals(dto.getCondition())){
                        Criteria smaller = new Criteria().where(key).lt(dto.getStatisticValue());
                        andOpers.add(smaller);
                    }else if("inputEqual".equals(dto.getCondition())){
                        Criteria input = new Criteria().where(key).regex(".*?" + dto.getStatisticValue() + ".*");
                        orOpers.add(input);
                    }else if("inputNotEqual".equals(dto.getCondition())){
                        Criteria input = new Criteria().where(key).not().regex(".*?" + dto.getStatisticValue() + ".*");
                        andOpers.add(input);
                    }
                }
                if(andOpers.size()>0){
                    criteria.andOperator(andOpers.toArray(new Criteria[andOpers.size()]));
                }
                if(orOpers.size()>0){
                    criteria.orOperator(orOpers.toArray(new Criteria[orOpers.size()]));
                }

                query.addCriteria(criteria);
            }
        }
        resultDTO.setDiyStatisticResponseName("result");
        resultDTO.setDiyStatisticResponseValue((int)mongoTemplate.count(query,"surveyQuestion"));
        resultList.add(resultDTO);
        resultDTO = new DiyStatisticResponseDTO();
        resultDTO.setDiyStatisticResponseName("total");
        resultDTO.setDiyStatisticResponseValue((int)mongoTemplate.count(new Query(),"surveyQuestion"));
        resultList.add(resultDTO);
    }

    @Override
    public void arrangementSurveyInfo() {
        List<String> idCards = surveyDao.getSurveyIdCard();
        for(String idCard : idCards){
            List<AnswerDTO> list = surveyDao.getSurveyAnswer(idCard);
            surveyDao.batchCreateSurveyAnswerFinal(list);
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            setBaseAtr(list,surveyQuestion);
            Query query=new Query().addCriteria(Criteria.where("idCard").is(surveyQuestion.getIdCard()));
            List flag = mongoTemplate.find(query,surveyQuestion.getClass());
            if(flag==null || flag.size()==0){
                mongoTemplate.insert(surveyQuestion);
            }
        }
    }

    private SurveyQuestion setBaseAtr(List<AnswerDTO> list, SurveyQuestion surveyQuestion){
        try {
            List<Field> fieldList = new ArrayList<>() ;
            Class tempClass = surveyQuestion.getClass();
            while (tempClass !=null && !tempClass.getName().toLowerCase().equals("java.lang.object") )
            {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            for(AnswerDTO keys : list){
                for (Field field : fieldList) {
                    String keysStr = "question" + keys.getQuestionId();
                    if (field.getName().equals(keysStr)) {
                        field.setAccessible(true);
                        Object value = keys.getQuestionAnswer();
                        if(value.equals(null)){
                            value = "";
                        }else{
                            value = String.valueOf(value);
                        }
                        field.set(surveyQuestion, value);
                        break;
                    }
                }
            }
            surveyQuestion.setIdCard(list.get(0).getElderIdentityNum());
            surveyQuestion.setWorkerName(list.get(0).getWorkerName());
            surveyQuestion.setWorkerPhone(list.get(0).getWorkerPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyQuestion;
    }
}
