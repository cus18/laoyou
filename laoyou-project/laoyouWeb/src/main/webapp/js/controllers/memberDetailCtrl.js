/**
 * Created by 郑强丽 on 2017/6/15.
 */
angular.module('controllers',[]).controller('memberDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','$timeout','$filter','GetDoctorList',
        'AssignedDoctor','GetOnGoingHealthServicePackageList','GetOnGoingHealthServicePackage','GetHealthServicePackageTemplateDetail',
        '$sce','GetHealthArchiveBasicInfo','GetHealthArchivePhysicalExaminationList','GetHealthArchiveHealthAssessmentList',
        'GetHealthArchiveHealthAssessmentResult','TestReportList','DiagnoseReportList','GetDietPlanByDate','GetMedicationPlan',
        'GetDetectionHealthData','GetMedicationPlanTimingByElderUserID','$location','$anchorScroll','GetMemberInfo','HospitalUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,$timeout,$filter,GetDoctorList,
                  AssignedDoctor,GetOnGoingHealthServicePackageList,GetOnGoingHealthServicePackage,GetHealthServicePackageTemplateDetail,
                  $sce,GetHealthArchiveBasicInfo,GetHealthArchivePhysicalExaminationList,GetHealthArchiveHealthAssessmentList,
                  GetHealthArchiveHealthAssessmentResult,TestReportList,DiagnoseReportList,GetDietPlanByDate,GetMedicationPlan,
                  GetDetectionHealthData,GetMedicationPlanTimingByElderUserID,$location,$anchorScroll,GetMemberInfo,HospitalUtil) {

            $scope.menu = $stateParams.menu;
            $scope.elderId = $stateParams.elderId;
            $scope.owner = {};
            $scope.searchStartDate = '2017-06-01';
            $scope.noneDatas = '没有更多数据了';


            $scope.goMemberDetail = function(menu){
                $state.go('memberDetail',{menu:menu})
            }

            GetMemberInfo.get({id:$scope.elderId},function(data){
                if(data.responseData){
                    $scope.elderName = data.responseData.elderName;
                    $scope.owner.doctorName = data.responseData.doctorName;
                    $scope.owner.nurseName = data.responseData.nurseName;
                }
            })

            $scope.loadPageList = function(){
                if($scope.menu == 'ownerDoctor')
                {
                    GetDoctorList.get({searchValue:$scope.param.searchValue, pageNo:$scope.pageNo,
                        pageSize:$scope.pageSize,type:1},function(data){
                        if(data.responseData){
                            $scope.response = data.responseData;
                        }
                    })
                }
                else if($scope.menu == 'ownerNurse')
                {
                    GetDoctorList.get({searchValue:$scope.param.searchValue, pageNo:$scope.pageNo,
                        pageSize:$scope.pageSize,type:2},function(data){
                        if(data.responseData){
                            $scope.response = data.responseData;
                        }
                    })
                }
            }

            if($scope.menu == 'ownerDoctor')
            {
                $("#page-tool").show();

                $scope.selectOwnerDoctor = function(){
                    var doctor = $scope.owner.ownerDoctor.split('-');
                    AssignedDoctor.save({
                        id:doctor[0],
                        doctorName:doctor[1],
                        elderID:$scope.elderId,
                        type:1
                    },function(data){
                        if(data.result == Global.SUCCESS){
                            $scope.owner.doctorName = doctor[1];
                            $scope.ownerHint = true;
                            $timeout(function(){$scope.ownerHint = false;},1500)
                        }
                    })
                }

            }
            else if($scope.menu == 'ownerNurse')
            {
                $("#page-tool").show();

                $scope.selectOwnerDoctor = function(){
                    var nurse = $scope.owner.ownerNurse.split('-');
                    AssignedDoctor.save({
                        id:nurse[0],
                        doctorName:nurse[1],
                        elderID:$scope.elderId,
                        type:2
                    },function(data){
                        if(data.result == Global.SUCCESS){
                            $scope.owner.nurseName = nurse[1];
                            $scope.ownerHint = true;
                            $timeout(function(){$scope.ownerHint = false;},1500)
                        }
                    })
                }

            }
            else if($scope.menu == 'servicePackage')
            {
                GetOnGoingHealthServicePackageList.save({pageNo:"1", pageSize:"50", orderType:"1",orderBy:"0",
                    requestData:{elderId:$scope.elderId}}, function (data) {
                    if(data.responseData){
                        $scope.healthServicePackageList = data.responseData;
                    }
                });

                //查看服务套餐详情
                $scope.serviceDetailShow = function(servicePackageId){
                    $scope.serviceDetail = !$scope.serviceDetail;
                    if($scope.serviceDetail){
                        GetOnGoingHealthServicePackage.get({healthServicePackageId:servicePackageId, elderId:$scope.elderId},function(data){
                            if(data.responseData){
                                $scope.healthServicePackageData = data.responseData;
                                GetHealthServicePackageTemplateDetail.get({healthServicePackageTemplateId:$scope.healthServicePackageData.servicePackageTemplateId},
                                    function(data){
                                        if(data.responseData){
                                            $scope.healthPackageTemplateDetail = $sce.trustAsHtml(data.responseData.healthServicePackageTemplateData);
                                        }
                                    });
                            }
                        });
                    }
                }
            }
            else if($scope.menu == 'healthArchives')
            {
                $scope.healthArchivesCont = function(cont){
                    if(cont == 'basicInfo'){
                        $('#detail-page').hide();
                        GetHealthArchiveBasicInfo.get({elderId:$scope.elderId},function(data){
                            if(data.responseData){
                                $scope.healthArchiveBasicInfo = data.responseData;
                            }
                        });
                    }else if(cont == 'physical'){
                        $('#detail-page').show();
                        $scope.detailPageList = function(){
                            GetHealthArchivePhysicalExaminationList.save({pageNo:$scope.pageNum, pageSize:$scope.pageSize,
                                orderType:"1",orderBy:"0",
                                requestData:{elderId:$scope.elderId}},function(data){
                                if(data.responseData){
                                    $scope.healthArchivePhysicalExaminationList = data.responseData;
                                    $scope.hint = '';
                                }else{
                                    $scope.hint = 'none';
                                }
                            });
                            $location.hash('wrapper');
                            $anchorScroll();
                        }
                        $scope.detailPageList();
                    }
                }
                $scope.healthArchivesCont('basicInfo');
            }
            else if($scope.menu == 'healthAssessment')
            {
                GetHealthArchiveHealthAssessmentList.save({pageNo:"1", pageSize:"50",
                    orderType:"1",orderBy:"0",
                    requestData:{elderId:$scope.elderId}},function(data){
                    if(data.responseData){
                        $scope.healthArchiveAssessmentList = data.responseData;
                    }
                });

                //查看评估结果
                $scope.showAssessmentResult = function(templateId,keyId){
                    $scope.healthAssessmentResult = !$scope.healthAssessmentResult;
                    if($scope.healthAssessmentResult){
                        GetHealthArchiveHealthAssessmentResult.get({healthAssessmentId:templateId,
                            keyId:keyId},function(data){
                            if(data.responseData){
                                $scope.healthAssessmentResult = data.responseData;
                            }

                            if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E924')
                            {
                                $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");

                                var answerResult = 0;
                                var badResult = "您好！根据简易智力状态检查（MMSE）评估，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士 目前有一定的老年痴呆症表现，并可能影响到您的日常生活、社会交际和工作能力，甚至还有意外走失的风险。我们会努力帮助您促进康复，希望得到您的理解及配合。";
                                var goodResult = "您好！根据简易智力状态检查（MMSE）评估，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士 目前智力状态正常，请保持健康的饮食和运动习惯，维护现有的健康状况。";
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
                                    $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士在未来10年内得2型糖尿病可能性较低，仅有1%，请保持健康的饮食和运动习惯，维护现有的健康状况，在运动方面应该控制在25到30千卡/每日。";
                                }
                                else if(answerResult >= 7 && answerResult <= 11)
                                {
                                    $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士在未来10年内得2型糖尿病可能性轻度升高，为4%，请保持健康的饮食和运动习惯，食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在25到30千卡/每日。";
                                }
                                else if(answerResult >= 12 && answerResult <= 14)
                                {
                                    $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士在未来10年内得2型糖尿病可能性中度升高，为17%,建议控制每日摄入食物的总热量，以达到或维持理想体重。食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在25到30千卡/每日。";
                                }
                                else if(answerResult >= 15 && answerResult <= 20)
                                {
                                    $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士在未来10年内得2型糖尿病可能性较高，为33%,建议控制每日摄入食物的总热量，以达到或维持理想体重。食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在30到35千卡/每日。";
                                }
                                else if(answerResult > 20)
                                {
                                    $scope.displayResult = "您好！根据糖尿病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士在未来10年内得2型糖尿病可能性非常高，为50%,建议控制每日摄入食物的总热量，食物的成分应该是低脂肪、适量蛋白质、高碳水化合物。坚持少量多餐，定时定量定餐。在运动方面应该控制在35到40千卡/每日。";
                                }

                            }
                            else if($scope.healthAssessmentResult.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E921')
                            {
                                $scope.healthAssessmentTemplateName = "脑卒中风险评估";
                                $scope.answer = $scope.healthAssessmentResult.healthAssessmentData.split(",");
                                var answerResult = 0;
                                var perfectResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士目前患心脑血管疾病的风险等级为极低风险，请保持健康的饮食和运动习惯，维护现有的健康状况。";
                                var goodResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士目前患心脑血管疾病的风险等级为低风险，建议要营养均衡，保持健康的饮食和运动习惯，维护现有的健康状况。";
                                var ordinaryResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士目前患心脑血管疾病的风险等级为中度风险，建议要营养均衡，不偏食不挑食，控制进食总量。适度运动，每天进行适度的有氧训练，保持平和而愉悦的心态。";
                                var badResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士目前患心脑血管疾病的风险等级为高风险，建议要营养均衡，不偏食不挑食，控制进食总量，应保证只吃八分饱。适度运动，每天进行适度的有氧训练，保持平和而愉悦的心态。";
                                var terribleResult = "您好！根据心脑血管疾病风险评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士目前患心脑血管疾病的风险等级为很高风险，建议要营养均衡，不偏食不挑食，控制进食总量，应保证只吃八分饱。适度运动，每天进行适度的有氧训练，保持心态平和，坏心情是导致心血管疾病的诱因。如果心情总是大起大伏，会导致心跳速度和血压水平不平稳，患心血管病几率也比别人高。因此在日常中，要保持平和而愉悦的心态。";
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
                                    $scope.displayResult = "您好！根据中医体质评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士 目前的体质类型为"+ habitus +"。建议通过相应的饮食和运动措施调节体质。";
                                }else{
                                    $scope.displayResult = "您好！根据中医体质评估结果，认定 " + $scope.healthAssessmentResult.elderName + " 先生/女士 目前的体质类型为"+ habitus +"，有" + secondHabitus + "的倾向。建议通过相应的饮食和运动措施调节体质。";
                                }
                            }

                        });
                    }


                }
            }
            else if($scope.menu == 'detection')
            {
                $scope.detectionCont = function(cont){

                   var initHealthData = function(num){
                       var date = new Date();
                       if(cont == 'bloodSugar'){
                           for(var i=0; i<num; i++)
                           {
                               var bloodSugar = {};
                               var value = {
                                   date : HospitalUtil.getAddDate(date.getTime(),0-i),
                                   bloodSugar : bloodSugar
                               }
                               $scope.healthDatas.push(value);
                           }
                       }
                       else if(cont == 'bloodPresser')
                       {
                           for(var i=0; i<num; i++)
                           {
                               var bloodPressure = {};
                               var value = {
                                   date : HospitalUtil.getAddDate(date.getTime(),0-i),
                                   bloodPressure : bloodPressure
                               }
                               $scope.healthDatas.push(value);
                           }
                       }
                   };

                   var arrangeHealthData = function(healthData, dataResponse){
                       if(cont == 'bloodSugar'){
                           if(dataResponse.period=='dawn')
                           {
                               healthData.bloodSugar.dawn = dataResponse.result;
                               healthData.bloodSugar.dawnTime = dataResponse.measureTime;
                               healthData.bloodSugar.dawnRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='beforeBreakFast')
                           {
                               healthData.bloodSugar.beforeBreakFast = dataResponse.result;
                               healthData.bloodSugar.beforeBreakFastTime = dataResponse.measureTime;
                               healthData.bloodSugar.beforeBreakFastRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='afterBreakFast')
                           {
                               healthData.bloodSugar.afterBreakFast = dataResponse.result;
                               healthData.bloodSugar.afterBreakFastTime = dataResponse.measureTime;
                               healthData.bloodSugar.afterBreakFastRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='beforeLunch')
                           {
                               healthData.bloodSugar.beforeLunch = dataResponse.result;
                               healthData.bloodSugar.beforeLunchTime = dataResponse.measureTime;
                               healthData.bloodSugar.beforeLunchRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='afterLunch')
                           {
                               healthData.bloodSugar.afterLunch = dataResponse.result;
                               healthData.bloodSugar.afterLunchTime = dataResponse.measureTime;
                               healthData.bloodSugar.afterLunchRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='beforeDinner')
                           {
                               healthData.bloodSugar.beforeDinner = dataResponse.result;
                               healthData.bloodSugar.beforeDinnerTime = dataResponse.measureTime;
                               healthData.bloodSugar.beforeDinnerRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='afterDinner')
                           {
                               healthData.bloodSugar.afterDinner = dataResponse.result;
                               healthData.bloodSugar.afterDinnerTime = dataResponse.measureTime;
                               healthData.bloodSugar.afterDinnerRemark = dataResponse.remark;
                           }
                           else if(dataResponse.period=='beforeSleep')
                           {
                               healthData.bloodSugar.beforeSleep = dataResponse.result;
                               healthData.bloodSugar.beforeSleepTime = dataResponse.measureTime;
                               healthData.bloodSugar.beforeSleepRemark = dataResponse.remark;
                           }
                       }
                   }

                   $scope.detection = {
                       'time' : '最近一周'
                   };
                   $scope.chooseHealthDataTime = function(){
                       $scope.dataLoad = false;
                       $scope.healthDatas = [];
                       $scope.charts = [];

                       if($scope.detection.time =='最近一周'){
                           $scope.timeType = 'week';
                           initHealthData(7);
                       }
                       else if($scope.detection.time == '最近一个月'){
                           $scope.timeType = 'month';
                           initHealthData(30);
                       }
                       else if($scope.detection.time == '最近三个月'){
                           $scope.timeType = 'threeMonth';
                           initHealthData(90);
                       }
                       else if($scope.detection.time == '最近半年'){
                           $scope.timeType = 'halfYear';
                           initHealthData(180);
                       }

                       if(cont == 'bloodSugar')
                       {
                           $scope.charts.bloodSugar = {};
                           $scope.charts.categories = [];
                           $scope.charts.bloodSugar.title = {text: ''};

                           $scope.loadingStatus = true;
                           GetDetectionHealthData.get({detectionType:'bg',
                               detectionDateType:$scope.timeType,elderId:$scope.elderId},function(data){
                               if(data.responseData!=undefined){
                                   angular.forEach($scope.healthDatas, function(healthData,index,array){
                                       angular.forEach(data.responseData.detectionData, function(dataResponse,index,array){
                                           if($filter('date')((new Date(dataResponse.measureTime)).getTime(),'yyyy-MM-dd')==healthData.date)
                                           {
                                               arrangeHealthData(healthData, dataResponse);
                                           }
                                       });
                                   });


                                   if(cont == 'bloodSugar')
                                   {
                                       $scope.charts.bloodSugar.dawnSeries = [{type: 'area', name: '凌晨血糖', data: []}];
                                       $scope.charts.bloodSugar.beforeBreakFastSeries = [{type: 'area', name: '空腹血糖', data: []}];
                                       $scope.charts.bloodSugar.afterBreakFastSeries = [{type: 'area', name: '早餐后血糖', data: []}];
                                       $scope.charts.bloodSugar.beforeLunchSeries = [{type: 'area', name: '午餐前血糖', data: []}];
                                       $scope.charts.bloodSugar.afterLunchSeries = [{type: 'area', name: '午餐后血糖', data: []}];
                                       $scope.charts.bloodSugar.beforeDinnerSeries = [{type: 'area', name: '晚餐前血糖', data: []}];
                                       $scope.charts.bloodSugar.afterDinnerSeries = [{type: 'area', name: '晚餐后血糖', data: []}];
                                       $scope.charts.bloodSugar.beforeSleepSeries = [{type: 'area', name: '睡前血糖', data: []}];

                                       angular.forEach($scope.healthDatas,function(data,index,array) {
                                           if (data.bloodSugar.dawn != undefined) {
                                               $scope.charts.bloodSugar.dawnSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.dawn)]);
                                           }
                                           else {
                                               $scope.charts.bloodSugar.dawnSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.beforeBreakFast!=undefined)
                                           {
                                               $scope.charts.bloodSugar.beforeBreakFastSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.beforeBreakFast)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.beforeBreakFastSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.afterBreakFast!=undefined)
                                           {
                                               $scope.charts.bloodSugar.afterBreakFastSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.afterBreakFast)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.afterBreakFastSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.beforeLunch!=undefined)
                                           {
                                               $scope.charts.bloodSugar.beforeLunchSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.beforeLunch)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.beforeLunchSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.afterLunch!=undefined)
                                           {
                                               $scope.charts.bloodSugar.afterLunchSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.afterLunch)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.afterLunchSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.beforeDinner!=undefined)
                                           {
                                               $scope.charts.bloodSugar.beforeDinnerSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.beforeDinner)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.beforeDinnerSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.afterDinner!=undefined)
                                           {
                                               $scope.charts.bloodSugar.afterDinnerSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.afterDinner)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.afterDinnerSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                           if(data.bloodSugar.beforeSleep!=undefined)
                                           {
                                               $scope.charts.bloodSugar.beforeSleepSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   parseFloat(data.bloodSugar.beforeSleep)]);
                                           }
                                           else
                                           {
                                               $scope.charts.bloodSugar.beforeSleepSeries[0].data.push([
                                                   new Date($filter('date')((new Date(data.date)).getTime(), 'yyyy-MM-dd')).getTime(),
                                                   null]);
                                           }
                                       })

                                       $scope.charts.bloodSugar.options =  {
                                           chart: {
                                               zoomType: 'x'
                                           },
                                           xAxis: {
                                               type: 'datetime',
                                               dateTimeLabelFormats: {
                                                   millisecond: '%H:%M:%S.%L',
                                                   second: '%H:%M:%S',
                                                   minute: '%H:%M',
                                                   hour: '%H:%M',
                                                   day: '%m-%d',
                                                   week: '%m-%d',
                                                   month: '%Y-%m',
                                                   year: '%Y'
                                               },
                                               gridLineColor:'#cef5f1',
                                               lineColor:'#cef5f1'
                                           },
                                           tooltip: {
                                               dateTimeLabelFormats: {
                                                   millisecond: '%H:%M:%S.%L',
                                                   second: '%H:%M:%S',
                                                   minute: '%H:%M',
                                                   hour: '%H:%M',
                                                   day: '%Y-%m-%d',
                                                   week: '%m-%d',
                                                   month: '%Y-%m',
                                                   year: '%Y'
                                               }
                                           },
                                           yAxis: {
                                               title: {
                                                   text: ''
                                               },
                                               gridLineColor:'#cef5f1',
                                               lineColor:'#cef5f1'
                                           },
                                           legend:{
                                               enabled: false
                                           },
                                           plotOptions: {
                                               area: {
                                                   fillColor: {
                                                       linearGradient: {
                                                           x1: 0,
                                                           y1: 0,
                                                           x2: 0,
                                                           y2: 1
                                                       },
                                                       stops: [
                                                           [0, Highcharts.getOptions().colors[0]],
                                                           [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                                       ]
                                                   },
                                                   marker: {
                                                       radius: 2
                                                   },
                                                   lineWidth: 1,
                                                   states: {
                                                       hover: {
                                                           lineWidth: 1
                                                       }
                                                   },
                                                   threshold: null
                                               }
                                           },
                                           credits: {		//去除右下角highcharts标志
                                               enabled: false
                                           },
                                           exporting: {	//去除右上角导出按钮
                                               enabled: false
                                           }
                                       };
                                   }

                                   $scope.dataLoad = true;

                               }
                           })
                       }
                       else if(cont == 'bloodPresser')
                       {
                           $scope.charts.bloodPressure = {};
                           $scope.charts.bloodPressure.title = {text: ''};
                           $scope.loadingStatus = true;

                           GetDetectionHealthData.get({detectionType:'bp',
                               detectionDateType:$scope.timeType,elderId:$scope.elderId},function(data){
                               if(data.responseData!=undefined) {
                                   $scope.charts.bloodPressure.pressureSeries = [{type: 'area', name: '舒张压', data: []},{type: 'area', name: '收缩压', data: []}];
                                   $scope.charts.bloodPressure.heartRateSeries = [{type: 'area', name: '心率', data: []}];

                                   angular.forEach(data.responseData.detectionData,function(value,index,array){
                                       $scope.charts.bloodPressure.pressureSeries[0].data.push([
                                           new Date(value.measureTime).getTime(),
                                           parseFloat(value.diastolic)]);
                                       $scope.charts.bloodPressure.pressureSeries[1].data.push([
                                           new Date(value.measureTime).getTime(),
                                           parseFloat(value.systolic)]);
                                       $scope.charts.bloodPressure.heartRateSeries[0].data.push([
                                           new Date(value.measureTime).getTime(),
                                           parseFloat(value.heartRate)]);
                                   })
                                   $scope.healthDatas = data.responseData.detectionData;

                               }

                               $scope.charts.bloodPressure.options = {
                                   chart: {
                                       zoomType: 'x'
                                   },
                                   xAxis: {
                                       type: 'datetime',
                                       dateTimeLabelFormats: {
                                           millisecond: '%H:%M:%S.%L',
                                           second: '%H:%M:%S',
                                           minute: '%H:%M',
                                           hour: '%H:%M',
                                           day: '%m-%d',
                                           week: '%m-%d',
                                           month: '%Y-%m',
                                           year: '%Y'
                                       },
                                       gridLineColor:'#cef5f1',
                                       lineColor:'#cef5f1'
                                   },
                                   tooltip: {
                                       dateTimeLabelFormats: {
                                           millisecond: '%H:%M:%S.%L',
                                           second: '%H:%M:%S',
                                           minute: '%H:%M',
                                           hour: '%H:%M',
                                           day: '%Y-%m-%d',
                                           week: '%m-%d',
                                           month: '%Y-%m',
                                           year: '%Y'
                                       }
                                   },
                                   yAxis: {
                                       title: {
                                           text: ''
                                       },
                                       gridLineColor:'#cef5f1',
                                       lineColor:'#cef5f1'
                                   },
                                   plotOptions: {
                                       area: {
                                           fillColor: {
                                               linearGradient: {
                                                   x1: 0,
                                                   y1: 0,
                                                   x2: 0,
                                                   y2: 1
                                               },
                                               stops: [
                                                   [0, Highcharts.getOptions().colors[0]],
                                                   [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                               ]
                                           },
                                           marker: {
                                               radius: 2
                                           },
                                           lineWidth: 1,
                                           states: {
                                               hover: {
                                                   lineWidth: 1
                                               }
                                           },
                                           threshold: null
                                       }
                                   },
                                   credits: {		//去除右下角highcharts标志
                                       enabled: false
                                   },
                                   exporting: {	//去除右上角导出按钮
                                       enabled: false
                                   }
                               };

                               $scope.dataLoad = true;
                           })

                       }
                   };
                   $scope.chooseHealthDataTime('week');



               }
                $scope.detectionCont('bloodSugar');
            }
            else if($scope.menu == 'testReport')
            {
                var testReportData = function(startDate,endDate){
                    TestReportList.get({elderId:$scope.elderId, startDate:startDate, endDate:endDate},
                        function(data) {
                            if(data.responseData){
                                $scope.testReportDatas = data.responseData;
                            }
                        })
                }
                testReportData($scope.searchStartDate,$filter('date')(new Date().getTime(),'yyyy-MM-dd'));

                $scope.testReportData = function(startDate,endDate){
                    testReportData(startDate,endDate)
                }
            }
            else if($scope.menu == 'diagnoseReport')
            {
                DiagnoseReportList.get({elderId:$scope.elderId, startDate:$scope.searchStartDate, endDate:$filter('date')(new Date().getTime(),'yyyy-MM-dd')},
                    function(data) {
                        if(data.responseData){
                            $scope.diagnoseReportDatas = data.responseData;
                            angular.forEach(data.responseData,function(data,index,array){
                                data.audioUrl = $sce.trustAsResourceUrl(data.audioUrl);
                            })
                        }
                    })
            }
            else if($scope.menu == 'medicineIntervention')
            {
                $scope.medicineCont = function(cont){
                    if(cont == 'plan'){
                        //服药干预
                        GetMedicationPlan.get({elderUserID:$scope.elderId},function(data){
                            if(data.responseData){
                                $scope.uncompletedMedicationPlan = data.responseData.uncompleted;
                                $scope.completeMedicationPlan = data.responseData.complete;
                                angular.forEach(data.responseData.uncompleted,function(data,index,array){
                                    data.repeat = data.repeat.replace('1','周一');
                                    data.repeat = data.repeat.replace('2','周二');
                                    data.repeat = data.repeat.replace('3','周三');
                                    data.repeat = data.repeat.replace('4','周四');
                                    data.repeat = data.repeat.replace('5','周五');
                                    data.repeat = data.repeat.replace('6','周六');
                                    data.repeat = data.repeat.replace('7','每天');
                                })
                                angular.forEach(data.responseData.complete,function(data,index,array){
                                    data.repeat = data.repeat.replace('1','周一');
                                    data.repeat = data.repeat.replace('2','周二');
                                    data.repeat = data.repeat.replace('3','周三');
                                    data.repeat = data.repeat.replace('4','周四');
                                    data.repeat = data.repeat.replace('5','周五');
                                    data.repeat = data.repeat.replace('6','周六');
                                    data.repeat = data.repeat.replace('7','每天');
                                })
                            }
                        });
                    }else if(cont == 'record'){
                        //服药记录
                        var medicationStatus = function(startDate,endDate){
                            GetMedicationPlanTimingByElderUserID.save({elderID:$scope.elderId,startTime:startDate,endTime:endDate},function(data){
                                if(data.responseData){
                                    $scope.medicationRecordDatas = data.responseData;
                                    angular.forEach(data.responseData,function(data,index,array){
                                        if(data.status == '0' || data.status == ''){
                                            data.status = '未服用';
                                        }else if(data.status == '1'){
                                            data.status = '按时服用';
                                        }
                                    })
                                }
                            });
                        }

                        medicationStatus($scope.searchStartDate,$filter('date')(new Date().getTime(),'yyyy-MM-dd'))
                        $scope.medicationStatus = function(startDate,endDate){
                            medicationStatus(startDate,endDate);
                        }
                    }
                }
                $scope.medicineCont('plan');

            }
            else if($scope.menu == 'mealRecord')
            {
                var dietPlanData = function(startDate,endDate){
                    GetDietPlanByDate.save({elderUserID:$scope.elderId,startDate:startDate,endDate:endDate},function(data){
                        if(data.responseData){
                            $scope.mealRecordDatas = data.responseData;
                        }
                    });
                }
                dietPlanData($scope.searchStartDate,$filter('date')(new Date().getTime(),'yyyy-MM-dd'));
                $scope.dietPlanData = function(startDate,endDate){
                    dietPlanData(startDate,endDate)
                }

            }
            else if($scope.menu == 'sportRecord')
            {

                /*运动记录*/
                $scope.sport = {};
                $scope.sportCharts = {};
                $scope.dataLoad = false;
                $scope.sportDate = [];
                $scope.sportDateX = [];
                $scope.sport.sportTit = '本周运动量';
                $scope.sport.sportStep = 0;
                $scope.sport.sportKilometer = 0;
                $scope.sport.sportKilocalorie = 0;
                $scope.sportSeries = [{
                    name: '步数',
                    data: []
                }, {
                    name: '热量',
                    data: []
                }, {
                    name: '距离',
                    data: []
                }];

                $scope.sportCharts.options = {
                    title:'',
                    chart: {
                        type: 'column'
                    },
                    xAxis: {
                        categories: $scope.sportDateX,
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: ''
                        }
                    },
                    // series: $scope.sportSeries,
                    legend: {
                        enabled: false			//隐藏data中的name显示
                    },
                    credits: {		            //去除右下角highcharts标志
                        enabled: false
                    }
                };
                GetDetectionHealthData.get({detectionType:'pdr',
                    detectionDateType:'week',elderId:$scope.elderId},function(data) {
                    if(data.responseData!=undefined){
                        angular.forEach($scope.sportDate,function(valueDate,index,array){
                            var existDate = false;
                            angular.forEach(data.responseData.detectionData,function(value,index,array){
                                if($filter('date')((new Date(value.measureTime)).getTime(),'yyyy-MM-dd')==valueDate)
                                {
                                    existDate=true;
                                    $scope.sportSeries[0].data.push(parseInt(value.stepCount));
                                    $scope.sportSeries[1].data.push(parseInt(value.consumeHeat));
                                    $scope.sportSeries[2].data.push(parseInt(value.stepLength));
                                    $scope.sport.sportStep = $scope.sport.sportStep + parseInt(value.stepCount);
                                    $scope.sport.sportKilocalorie = $scope.sport.sportKilocalorie + parseInt(value.consumeHeat);
                                    $scope.sport.sportKilometer = $scope.sport.sportKilometer + parseInt(value.stepLength);
                                }
                            });
                            if(!existDate){
                                $scope.sportSeries[0].data.push(0);
                                $scope.sportSeries[1].data.push(0);
                                $scope.sportSeries[2].data.push(0);
                            }
                        })

                    }

                });

            }

            //calendar
            $timeout(function(){
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000)


        }])
