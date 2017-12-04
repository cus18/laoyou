angular.module('controllers',[]).controller('healthAssessmentResultCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetHealthArchiveHealthAssessmentResult',
        function ($scope,$rootScope,$stateParams,$state,GetHealthArchiveHealthAssessmentResult) {

            $scope.loadingStatus = true;

            $scope.existHealthAssessmentId = $stateParams.existHealthAssessmentId;
            $scope.keyId = $stateParams.keyId;

            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            GetHealthArchiveHealthAssessmentResult.get({healthAssessmentId:$scope.existHealthAssessmentId,
                keyId:$scope.keyId},function(data){

                $scope.loadingStatus = false;
                $scope.healthAssessmentResult = data.responseData;

                if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E924')
                {
                    $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");
                    var answerResult = 0;
                    var badResult = "您好！根据简易智力状态检查（MMSE）评估，认定 " + $scope.elderName + " 先生/女士 目前有一定的老年痴呆症表现，并可能影响到您的日常生活、社会交际和工作能力，甚至还有意外走失的风险。我们会努力帮助您促进康复，希望得到您的理解及配合。";
                    var goodResult = "您好！根据简易智力状态检查（MMSE）评估，认定 " + $scope.elderName + " 先生/女士 目前智力状态正常，请保持健康的饮食和运动习惯，维护现有的健康状况。";
                    $scope.displayResult = "";

                    for(var i=2;i<=$scope.answer.length-2;i++)
                    {
                        if($scope.answer[i]=="true")
                        {
                            answerResult++;
                        }

                    }

                    if($scope.answer[1]=="文盲")
                    {
                        if(answerResult<=17)
                        {
                            $scope.displayResult = badResult;
                        }
                        else
                        {
                            $scope.displayResult = goodResult;
                        }

                    }
                    if($scope.answer[1]=="小学")
                    {
                        if(answerResult<=20)
                        {
                            $scope.displayResult = badResult;
                        }
                        else
                        {
                            $scope.displayResult = goodResult;
                        }

                    }
                    if($scope.answer[1]=="初中"||$scope.answer[1]=="高中"||$scope.answer[1]=="中专")
                    {
                        if(answerResult<=22)
                        {
                            $scope.displayResult = badResult;
                        }
                        else
                        {
                            $scope.displayResult = goodResult;
                        }

                    }
                    if($scope.answer[1]=="大学"||$scope.answer[1]=="硕士"||$scope.answer[1]=="博士")
                    {
                        if(answerResult<=23)
                        {
                            $scope.displayResult = badResult;
                        }
                        else
                        {
                            $scope.displayResult = goodResult;
                        }

                    }
                }
                else if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E922')
                {
                    $scope.healthAssessmentTemplateName = "糖尿病风险评估";
                    $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");
                    var answerResult = 0;
                    $scope.displayResult = "";

                    if($scope.answer[2] >= 45 && $scope.answer[2] <= 54)           //年龄
                    {
                        answerResult += 2;
                    }
                    else if($scope.answer[2] >= 55 && $scope.answer[2] <= 64)
                    {
                        answerResult += 3;
                    }
                    else if($scope.answer[2] > 64)
                    {
                        answerResult += 4;
                    }

                    if($scope.answer[3] >= 24 && $scope.answer[3] <= 28)           //体重指数
                    {
                        answerResult += 1;
                    }
                    else if($scope.answer[3] > 28)
                    {
                        answerResult += 3;
                    }

                    if($scope.answer[1] == '男')         //腰围跟性别
                    {
                        if($scope.answer[4] >= 85 && $scope.answer[4] <= 95)
                        {
                            answerResult += 3;
                        }
                        else if($scope.answer[4] > 95)
                        {
                            answerResult += 4;
                        }
                    }
                    else if($scope.answer[1] == '女')
                    {
                        if($scope.answer[4] >= 80 && $scope.answer[4] <= 90)
                        {
                            answerResult += 3;
                        }
                        else if($scope.answer[4] > 90)
                        {
                            answerResult += 4;
                        }
                    }

                    if($scope.answer[5] == 'true')          //每天的运动时间
                    {
                        answerResult += 2;
                    }

                    if($scope.answer[6] == 'true')          //摄入水果
                    {
                        answerResult += 1;
                    }

                    if($scope.answer[7] == 'true')          //摄入蔬菜
                    {
                        answerResult += 1;
                    }

                    if($scope.answer[8] == 'true')          //服用药品
                    {
                        answerResult += 2;
                    }

                    if($scope.answer[9] > 6.1)          //空腹血糖值
                    {
                        answerResult += 5;
                    }

                    if($scope.answer[10] == '有 爷爷/姥爷、奶奶/姥姥、姑妈/姨妈、叔、伯/舅、表兄妹/堂兄妹(或其子女)')
                    {
                        answerResult += 3;
                    }
                    else if($scope.answer[10] == '有 父母、兄弟姐妹、子女')
                    {
                        answerResult += 5;
                    }

                    if(answerResult < 7)
                    {
                        $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.elderName + " 先生/女士在未来10年内得2型糖尿病可能性较低，仅有1%，请保持健康的饮食和运动习惯，维护现有的健康状况，在运动方面应该控制在25到30千卡/每日。";
                    }
                    else if(answerResult >= 7 && answerResult <= 11)
                    {
                        $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.elderName + " 先生/女士在未来10年内得2型糖尿病可能性轻度升高，为4%，请保持健康的饮食和运动习惯，食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在25到30千卡/每日。";
                    }
                    else if(answerResult >= 12 && answerResult <= 14)
                    {
                        $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.elderName + " 先生/女士在未来10年内得2型糖尿病可能性中度升高，为17%,建议控制每日摄入食物的总热量，以达到或维持理想体重。食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在25到30千卡/每日。";
                    }
                    else if(answerResult >= 15 && answerResult <= 20)
                    {
                        $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.elderName + " 先生/女士在未来10年内得2型糖尿病可能性较高，为33%,建议控制每日摄入食物的总热量，以达到或维持理想体重。食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在30到35千卡/每日。";
                    }
                    else if(answerResult > 20)
                    {
                        $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.elderName + " 先生/女士在未来10年内得2型糖尿病可能性非常高，为50%,建议控制每日摄入食物的总热量，食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在35到40千卡/每日。";
                    }

                }
                else if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E921')
                {
                    $scope.healthAssessmentTemplateName = "脑卒中风险评估";
                    $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");
                    var answerResult = 0;
                    var perfectResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.elderName + " 先生/女士目前患心脑血管疾病的风险等级为极低风险，请保持健康的饮食和运动习惯，维护现有的健康状况。";
                    var goodResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.elderName + " 先生/女士目前患心脑血管疾病的风险等级为低风险，建议要营养均衡，保持健康的饮食和运动习惯，维护现有的健康状况。";
                    var ordinaryResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.elderName + " 先生/女士目前患心脑血管疾病的风险等级为中度风险，建议要营养均衡，不偏食不挑食，控制进食总量。适度运动，每天进行适度的有氧训练，保持平和而愉悦的心态。";
                    var badResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.elderName + " 先生/女士目前患心脑血管疾病的风险等级为高风险，建议要营养均衡，不偏食不挑食，控制进食总量，应保证只吃八分饱。适度运动，每天进行适度的有氧训练，保持平和而愉悦的心态。";
                    var terribleResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.elderName + " 先生/女士目前患心脑血管疾病的风险等级为很高风险，建议要营养均衡，不偏食不挑食，控制进食总量，应保证只吃八分饱。适度运动，每天进行适度的有氧训练，保持心态平和，坏心情是导致心血管疾病的诱因。如果心情总是大起大伏，会导致心跳速度和血压水平不平稳，患心血管病几率也比别人高。因此在日常中，要保持平和而愉悦的心态。";
                    $scope.displayResult = "";

                    if($scope.answer[2] >= 40 && $scope.answer[2] <= 44)           //年龄
                    {
                        answerResult += 1;
                    }
                    else if($scope.answer[2] >= 45 && $scope.answer[2] <= 49)
                    {
                        answerResult += 2;
                    }
                    else if($scope.answer[2] >= 50 && $scope.answer[2] <= 54)
                    {
                        answerResult += 3;
                    }
                    else if($scope.answer[2] >= 55 && $scope.answer[2] <= 59)
                    {
                        answerResult += 4;
                    }

                    if($scope.answer[3] >= 24 && $scope.answer[3] < 28)            //体重指数
                    {
                        answerResult += 1;
                    }
                    else if($scope.answer[3] >= 28){
                        answerResult += 2;
                    }

                    if($scope.answer[7] >= 5.2)      //胆固醇
                    {
                        answerResult += 1;
                    }


                    if($scope.answer[1] == '男')
                    {
                        if($scope.answer[4] == 'true')          //是否抽烟
                        {
                            answerResult += 2;
                        }

                        if($scope.answer[5] == 'true')          //是否患过糖尿病
                        {
                            answerResult += 1;
                        }

                        if($scope.answer[6] < 120){         //收缩压
                            answerResult -= 2;
                        }
                        else if($scope.answer[6] >= 130 && $scope.answer[6] < 140)
                        {
                            answerResult += 1;
                        }
                        else if($scope.answer[6] >= 140 && $scope.answer[6] < 160)
                        {
                            answerResult += 2;
                        }
                        else if($scope.answer[6] >= 160 && $scope.answer[6] < 180)
                        {
                            answerResult += 5;
                        }
                        else if($scope.answer[6] >= 180)
                        {
                            answerResult += 8;
                        }

                        if(answerResult <= 7)               //结果
                        {
                            $scope.displayResult = perfectResult;
                        }
                        else if(answerResult >= 8 && answerResult <= 10)
                        {
                            $scope.displayResult = goodResult;
                        }
                        else if(answerResult == 11 || answerResult == 12)
                        {
                            $scope.displayResult = ordinaryResult;
                        }
                        else if(answerResult >= 13 || answerResult <= 15)
                        {
                            $scope.displayResult = badResult;
                        }
                        else if(answerResult >= 16)
                        {
                            $scope.displayResult = terribleResult;
                        }
                    }
                    else if($scope.answer[1] == '女')
                    {
                        if($scope.answer[4] == 'true')          //是否抽烟
                        {
                            answerResult += 1;
                        }

                        if($scope.answer[5] == 'true')          //是否患过糖尿病
                        {
                            answerResult += 2;
                        }

                        if($scope.answer[6] < 120){         //收缩压
                            answerResult -= 2;
                        }
                        else if($scope.answer[6] >= 130 && $scope.answer[6] < 140)
                        {
                            answerResult += 1;
                        }
                        else if($scope.answer[6] >= 140 && $scope.answer[6] < 160)
                        {
                            answerResult += 2;
                        }
                        else if($scope.answer[6] >= 160 && $scope.answer[6] < 180)
                        {
                            answerResult += 3;
                        }
                        else if($scope.answer[6] >= 180)
                        {
                            answerResult += 4;
                        }

                        if(answerResult <= 7)               //结果
                        {
                            $scope.displayResult = perfectResult;
                        }
                        else if(answerResult == 8)
                        {
                            $scope.displayResult = goodResult;
                        }
                        else if(answerResult == 9 || answerResult == 10)
                        {
                            $scope.displayResult = ordinaryResult;
                        }
                        else if(answerResult == 11 || answerResult == 12)
                        {
                            $scope.displayResult = badResult;
                        }
                        else if(answerResult >= 13)
                        {
                            $scope.displayResult = terribleResult;
                        }

                    }
                }
                else if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E923')
                {
                    $scope.healthAssessmentTemplateName = "中医体质评估";
                    $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");
                    var answerResult = 0;
                    var habitus = "";
                    var secondHabitus = "";

                    function totalPoints(start,end){
                        for(var i = start; i < end; i++){
                            switch ($scope.answer[i]){
                                case '没有（根本不）':
                                    answerResult += 1;
                                    break;
                                case '很少（有一点）':
                                    answerResult += 2;
                                    break;
                                case '有时（有些）':
                                    answerResult += 3;
                                    break;
                                case '经常（相当）':
                                    answerResult += 4;
                                    break;
                                case '总是（非常）':
                                    answerResult += 5;
                                    break;
                            }
                        }
                        return answerResult;
                    }

                    function resultPoints(start,end){
                        answerResult = 0;
                        return ((totalPoints(start,end) - (end-start))/((end-start)*4)*100);
                    }

                    var resultPingHe = resultPoints(1,9);
                    var resultQiXu = resultPoints(9,17);
                    var resultYangXu = resultPoints(17,23);
                    var resultYinXu = resultPoints(23,31);
                    var resultTanShi = resultPoints(31,39);
                    var resultShiRe = resultPoints(39,46);
                    var resultXueYu = resultPoints(46,53);
                    var resultQiYu = resultPoints(53,60);
                    var resultTeBing = resultPoints(60,67);

                    if(resultPingHe >= 60)
                    {
                        if(resultQiXu < 40 && resultXuYang < 40 && resultYinXu < 40 && resultTanShi < 40 && resultShiRe < 40 && resultXueYu < 40 && resultQiYu < 40 && resultTeBing < 40){
                            habitus = " 平和质";
                        }
                        if(resultQiXu > 30){
                            secondHabitus += " 气虚质";
                        }
                        else if(resultYangXu > 30){
                            secondHabitus += " 阳虚质";
                        }
                        else if(resultYinXu > 30){
                            secondHabitus += " 阴虚质";
                        }
                        else if(resultTanShi > 30){
                            secondHabitus += " 痰湿质";
                        }
                        else if(resultShiRe > 30){
                            secondHabitus += " 湿热质";
                        }
                        else if(resultXueYu > 30){
                            secondHabitus += " 血淤质";
                        }
                        else if(resultQiYu > 30){
                            secondHabitus += " 气郁质";
                        }
                        else if(resultTeBing > 30){
                            secondHabitus += " 特禀质";
                        }
                    }
                    else
                    {
                        if(resultQiXu > 30){
                            habitus += " 气虚质";
                        }
                        if(resultYangXu > 30){
                            habitus += " 阳虚质";
                        }
                        if(resultYinXu > 30){
                            habitus += " 阴虚质";
                        }
                        if(resultTanShi > 30){
                            habitus += " 痰湿质";
                        }
                        if(resultShiRe > 30){
                            habitus += " 湿热质";
                        }
                        if(resultXueYu > 30){
                            habitus += " 血淤质";
                        }
                        if(resultQiYu > 30){
                            habitus += " 气郁质";
                        }
                        if(resultTeBing > 30){
                            habitus += " 特禀质";
                        }
                    }


                    if(secondHabitus == ''){
                        $scope.displayResult = "您好！根据中医体质评估结果，认定 " + $scope.elderName + " 先生/女士 目前的体质类型为"+ habitus +"。建议通过相应的饮食和运动措施调节体质。";
                    }else{
                        $scope.displayResult = "您好！根据中医体质评估结果，认定 " + $scope.elderName + " 先生/女士 目前的体质类型为"+ habitus +"，有" + secondHabitus + "的倾向。建议通过相应的饮食和运动措施调节体质。";
                    }
                }

            });


        }])
