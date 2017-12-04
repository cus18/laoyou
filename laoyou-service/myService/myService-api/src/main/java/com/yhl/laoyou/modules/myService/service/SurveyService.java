package com.yhl.laoyou.modules.myService.service;


import com.yhl.laoyou.common.dto.survey.*;
import com.yhl.laoyou.modules.sys.entity.User;

import java.util.List;

/**
 * Created by zbm84 on 2017/5/11.
 */

public interface SurveyService {

    List<SurveyDTO> getAllSurveyQuestions();

    void createOrUpdateAnswer(AnswerDTO answerDTO, User user) throws Exception;

    List<AnswerDTO> findSurveyAnswer(AnswerDTO answerDTO);

    List<SingleStatisticDTO> singleStatistic(SurveyDTO surveyDTO);

    List<List<List>> crossStatistic(List<SurveyDTO> independentVariableList, List<SurveyDTO> dependentVariableList);

    void diyStatistic(List<DiyStatisticRequestDTO> diyStatisticRequestDTOList,List<DiyStatisticResponseDTO> list);

    public void arrangementSurveyInfo();

}
