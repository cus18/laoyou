angular.module('controllers',[]).controller('surveyDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetTotalQuestion','Survey','Global',
        'GetQuestionAnswer','SaveQuestionAnswer','ElderUtil','$window','$ionicPopup',
        'GetProvinceData','GetAreaData','$ionicLoading','GetQRCodeURL','LoginOut',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetTotalQuestion,Survey,Global,
                  GetQuestionAnswer,SaveQuestionAnswer,ElderUtil,$window,$ionicPopup,
                  GetProvinceData,GetAreaData,$ionicLoading,GetQRCodeURL,LoginOut) {

            $scope.param = {
                town : Survey.town,
                answer: {},
                inputValue:'',
                beadhouse : false,
                selectedProvince:'',
                selectedCity:'',
                selectedTown:'',
                selectedVillage : '',
                selectedProvinceIndex : 0,
                QRCodeURL: ''
            }

            $scope.$watch('param.selectedProvince',function(){

                $scope.param.selectedProvinceIndex++;

                if($scope.param.selectedProvinceIndex>2)
                {
                    angular.forEach($scope.param.province, function(value,index,array){
                        if(value.name == $scope.param.selectedProvince)
                        {
                            GetAreaData.get({id:value.id},function(data){
                                $scope.param.city = data.responseData;
                            })
                        }
                    })
                }

            })

            $scope.$watch('param.selectedCity',function(){

                if($scope.param.selectedCity!="丰台区")
                {
                    $scope.param.selectedTown = "";
                    $scope.param.selectedVillage = "";
                    $scope.param.inputValue = "";
                    $scope.param.town = {};
                    $scope.param.village = {};
                }
                else{
                    $scope.param.town = Survey.town;
                }

            })

            $scope.$watch('param.selectedTown',function(){

                angular.forEach($scope.param.town, function(value,index,array){
                    if(value.name == $scope.param.selectedTown)
                    {
                        $scope.param.village = value.village;
                    }
                })

            })

            $scope.$watch('param.answerlock',function(){
                if(!$scope.param.answerlock)
                {
                    $ionicLoading.hide();
                }
                else
                {
                    $ionicLoading.show({
                        template: 'Loading...'
                    });
                }
            })

            var clearAnswerData = function()
            {
                $scope.param.answer = {};
                $scope.param.inputValue = '';
                $scope.selectedVillage = '';
                $scope.selectedProvince = '';
                $scope.selectedCity = '';
                $scope.selectedTown = '';
                $scope.selectedProvinceIndex = 0;
                $scope.param.singleChoice = '';
            }

            $scope.$on('$ionicView.enter', function(){
                GetTotalQuestion.save({},function(data){

                    if(data.result==Global.FAILURE)
                    {
                        if(data.errorInfo==Global.TOKEN_ERROR){
                            window.location.href = "surveyLogin";
                        }
                    }

                    $scope.param.questions = data.responseData;
                    $scope.param.questionIndex = 0;
                    $scope.param.questionMaxItem = $scope.param.questions.length;
                    refreshQuestion(angular.copy($scope.param.questions[$scope.param.questionIndex]));
                })
            });

            $scope.prevItem = function(){
                if($scope.param.questionIndex!=0)
                {
                    $scope.param.questionIndex--;
                }
                refreshQuestion(angular.copy($scope.param.questions[$scope.param.questionIndex]));
            }

            $scope.nextItem = function(flag){
                if(flag=='finish')
                {
                    $ionicPopup.confirm({
                        title: '确认是否提交问卷?',
                        cancelText:'取消',
                        okText: '确认'
                    }).then(function(res) {
                        if(res) {
                            nextItemOperation(flag);
                        }
                    });
                }
                else
                {
                    nextItemOperation(flag);
                }
            }

            var nextItemOperation = function(flag){

                //如果是填空题
                if($scope.param.currentQuestion.questionType == 'input')
                {
                    $scope.param.answer.question_answer = angular.copy($scope.param.inputValue);
                    if($scope.param.currentQuestion.questionStatus=="elderIdentityNum")
                    {
                        $scope.param.answer.elder_identity_num = angular.copy($scope.param.inputValue);
                        $scope.param.elder_identity_num = angular.copy($scope.param.inputValue);
                    }
                    else {
                        $scope.param.answer.elder_identity_num = angular.copy($scope.param.elder_identity_num);
                    }
                }
                //如果是单选题
                if($scope.param.currentQuestion.questionType == 'single')
                {
                    $scope.param.answer.question_answer = angular.copy($scope.param.singleChoice);
                    if($scope.param.currentQuestion.questionStatus=='beadhouse')
                    {
                        //设置老人是否常住机构的状态
                        if($scope.param.answer.question_answer=='A')
                        {
                            $scope.param.beadhouse = true;
                        }
                        else if($scope.param.answer.question_answer=='B')
                        {
                            $scope.param.beadhouse = false;
                        }
                    }
                }
                //如果是复合选择题
                if($scope.param.currentQuestion.questionType == 'select')
                {
                    if($scope.param.currentQuestion.questionStatus=="censusDetailAddress")
                    {
                        $scope.param.answer.question_answer =
                            angular.copy($scope.param.selectedProvince) + ";" +
                            angular.copy($scope.param.selectedCity) + ";" +
                            angular.copy($scope.param.selectedTown) + ";" +
                            angular.copy($scope.param.selectedVillage) + ";" +
                            angular.copy($scope.param.inputValue);
                    }
                    if($scope.param.currentQuestion.questionStatus=="livingDetailAddress")
                    {
                        if($scope.param.beadhouse)
                        {
                            $scope.param.answer.question_answer =
                                angular.copy($scope.param.selectedProvince) + ";" +
                                angular.copy($scope.param.selectedCity) + ";" +
                                angular.copy($scope.param.inputValue);
                        }
                        else
                        {
                            $scope.param.answer.question_answer =
                                angular.copy($scope.param.selectedProvince) + ";" +
                                angular.copy($scope.param.selectedCity) + ";" +
                                angular.copy($scope.param.selectedTown) + ";" +
                                angular.copy($scope.param.selectedVillage) + ";" +
                                angular.copy($scope.param.inputValue);
                        }
                    }
                }
                //如果是多选题
                if($scope.param.currentQuestion.questionType == 'multi')
                {
                    $scope.param.answer.question_answer = '';
                    angular.forEach($scope.param.currentQuestion.questionData,function(value,index,array){
                        if(value.checked)
                        {
                            $scope.param.answer.question_answer = $scope.param.answer.question_answer + value.questionItem + ";"
                        }
                    });
                }

                //对答案进行校验
                if(!ElderUtil.CheckSurveyAnswerData($scope.param.currentQuestion,$scope.param.answer))
                {
                    return;
                }
                SaveQuestionAnswer.save($scope.param.answer,function(data){

                    if(data.result==Global.SUCCESS)
                    {
                        saveAnswerSuccessOperation(flag);
                    }
                    else if(data.result=="already")
                    {
                        $ionicPopup.confirm({
                            title: '此用户已做过问卷调查，是否进行重复录入?',
                            cancelText:'放弃',
                            okText: '确认'
                        }).then(function(res) {
                            if(res) {
                                saveAnswerSuccessOperation(flag);
                            }else{
                                $window.location.reload();
                            }
                        });

                    }

                })

            }

            var saveAnswerSuccessOperation = function(flag){
                //答案保存成功，清空数据
                clearAnswerData();

                if($scope.param.questionIndex<$scope.param.questionMaxItem)
                {
                    $scope.param.questionIndex++;
                }

                if(flag=='finish')
                {
                    $scope.param.surveyFlag = 'finish';
                }
                else if(flag=='next')
                {
                    refreshQuestion(angular.copy($scope.param.questions[$scope.param.questionIndex]));
                }
            }

            var refreshQuestion = function(currentQuestionTemp){

                clearAnswerData();

                $scope.param.answerlock = true;

                if(currentQuestionTemp.questionType == 'single')
                {
                    refreshQuestionAnswer(currentQuestionTemp);
                }
                if(currentQuestionTemp.questionType == 'multi')
                {
                    angular.forEach(currentQuestionTemp.questionData,
                        function(value,index,array){
                        value.checked = false;
                    });
                    refreshQuestionAnswer(currentQuestionTemp);
                }
                if(currentQuestionTemp.questionType == 'select')
                {
                    if(currentQuestionTemp.questionStatus=='censusDetailAddress'||
                        currentQuestionTemp.questionStatus=='livingDetailAddress')
                    {
                        initialAddressData(currentQuestionTemp);
                    }
                }
                else if(currentQuestionTemp.questionType == 'input')
                {
                    refreshQuestionAnswer(currentQuestionTemp);
                }

            }

            $scope.clickCheckBox = function(index){
                $scope.param.currentQuestion.questionData[index].checked =
                    !$scope.param.currentQuestion.questionData[index].checked;
            }

            $scope.createNewSurvey = function(){
                $window.location.reload();
            }

            var initialAddressData = function(currentQuestionTemp){
                GetProvinceData.save(function(data){
                    $scope.param.province = data.responseData;
                    //$scope.param.selectedProvince = '北京';
                    var provinceId = "1";
                    angular.forEach($scope.param.province,function (value,index,array) {
                        if($scope.param.selectedProvince==value.name)
                        {
                            provinceId = value.id
                        }
                    })
                    GetAreaData.get({id:provinceId},function(data){
                        $scope.param.city = data.responseData;
                        //$scope.param.selectedCity = '丰台区';
                        refreshQuestionAnswer(currentQuestionTemp);
                    })
                })
            }

            var refreshQuestionAnswer = function(currentQuestionTemp) {
                $scope.param.answer.question_id = angular.copy(currentQuestionTemp.questionId);
                $scope.param.answer.question_name = angular.copy(currentQuestionTemp.questionName);
                $scope.param.answer.elder_identity_num = $scope.param.elder_identity_num;

                //获取问题的答案，对于输入身份证这道题目，不用读取数据库数据
                if(currentQuestionTemp.questionStatus!="elderIdentityNum")
                {
                    GetQuestionAnswer.save($scope.param.answer,function(data){

                        if(data.responseData!=undefined)
                        {
                            if(currentQuestionTemp.questionType == 'input')
                            {
                                $scope.param.inputValue = data.responseData[0].question_answer;
                            }
                            else if(currentQuestionTemp.questionType == 'single')
                            {
                                $scope.param.singleChoice = data.responseData[0].question_answer;
                                //设置老人是否常住机构的状态
                                if($scope.param.answer.question_answer=='A')
                                {
                                    $scope.param.beadhouse = true;
                                }
                                else if($scope.param.answer.question_answer=='B')
                                {
                                    $scope.param.beadhouse = false;
                                }
                            }
                            else if(currentQuestionTemp.questionType == 'select')
                            {
                                var selectValue = data.responseData[0].question_answer.split(";");

                                if(currentQuestionTemp.questionStatus=="censusDetailAddress")
                                {
                                    $scope.param.selectedProvince = selectValue[0];
                                    $scope.param.selectedCity = selectValue[1];
                                    $scope.param.selectedTown = selectValue[2];
                                    $scope.param.selectedVillage = selectValue[3];
                                    $scope.param.inputValue = selectValue[4];
                                }
                                if(currentQuestionTemp.questionStatus=="livingDetailAddress")
                                {
                                    if(selectValue[4]==undefined)
                                    {
                                        $scope.param.selectedProvince = selectValue[0];
                                        $scope.param.selectedCity = selectValue[1];
                                        $scope.param.inputValue = selectValue[2];
                                    }
                                    else
                                    {
                                        $scope.param.selectedProvince = selectValue[0];
                                        $scope.param.selectedCity = selectValue[1];
                                        $scope.param.selectedTown = selectValue[2];
                                        $scope.param.selectedVillage = selectValue[3];
                                        $scope.param.inputValue = selectValue[4];
                                    }
                                }
                            }
                            else if(currentQuestionTemp.questionType == 'multi')
                            {
                                var selectValue = data.responseData[0].question_answer;
                                angular.forEach(currentQuestionTemp.questionData,function(value,index,array){
                                    if(selectValue.indexOf(value.questionItem) != -1)
                                    {
                                        value.checked = true;
                                    }
                                    else
                                    {
                                        value.checked = false;
                                    }
                                })
                            }
                        }
                        else
                        {
                            if(currentQuestionTemp.questionStatus=='elderBirthDay')
                            {
                                $scope.param.inputValue = ElderUtil.identityCount(angular.copy($scope.param.elder_identity_num),1);
                            }
                            if(currentQuestionTemp.questionStatus=='elderAge')
                            {
                                $scope.param.inputValue = ElderUtil.identityCount(angular.copy($scope.param.elder_identity_num),3);
                            }
                            if(currentQuestionTemp.questionStatus=='elderSex')
                            {
                                $scope.param.inputValue = ElderUtil.identityCount(angular.copy($scope.param.elder_identity_num),2);
                            }
                        }
                        $scope.param.answerlock = false;
                        $scope.param.currentQuestion = currentQuestionTemp;
                    })
                }
                else
                {
                    if($scope.param.answer.elder_identity_num!=undefined)
                    {
                        $scope.param.inputValue =  angular.copy($scope.param.answer.elder_identity_num);
                    }
                    $scope.param.answerlock = false;
                    $scope.param.currentQuestion = currentQuestionTemp;
                }
            }

            $scope.help = function()
            {
                alert( '采集过程中如有问题，请联系 李先生：17600222742 王女士：13466412901 陈先生：13601025662');
            }

            $scope.loginOut = function()
            {
                LoginOut.save({},function(data){
                    console.log(data);
                    if(data.result==Global.LOGIN_OUT)
                    {
                        window.location.href = "surveyLogin";
                    }
                })

            }

}])
    .filter('titlecut', function() { //可以注入依赖
    return function(text) {
        if(text!=undefined)
        {
            if(text.length<10)
            {
                return text;
            }
            else
            {
                return text.substring(0,9)+"...";
            }

        }
    }
});