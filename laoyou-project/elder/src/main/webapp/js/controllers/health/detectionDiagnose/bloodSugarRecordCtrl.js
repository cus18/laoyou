/**
 * Created by 郑强丽 on 2017/5/20.
 */
angular.module('controllers',[]).controller('bloodSugarRecordCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','CreateDetection','GetControlTarget','$filter','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,CreateDetection,GetControlTarget,$filter,ElderUtil) {

            $scope.loadingStatus = true;

            $scope.goMenu = function(firstMenuParam,secondMenuParam){
                $state.go("detectionDiagnose", {firstMenu: firstMenuParam, secondMenu: secondMenuParam});
            }

            $scope.enterGroupTalk = function(){
                window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
            }

            $scope.bloodSugar = {};


            /*提交*/
            var submitOnOff = true;        //防止多次提交
            $scope.submitForm = function(){
                if($scope.bloodSugar.measureNum && $scope.bloodSugar.measureType){
                    /*测量时间*/
                    if($scope.bloodSugar.measureTime){
                        var time = $scope.bloodSugar.measureDate + ' ' + $scope.bloodSugar.measureTime;
                    }else{
                        var time = $scope.bloodSugar.nowDate + ' ' + $scope.bloodSugar.nowTime;
                    }
                    /*测量类型*/
                    switch($scope.bloodSugar.measureType){
                        case '凌晨':
                            $scope.bloodSugar.measureType = 'dawn';
                            break;
                        case '早餐前/空腹':
                            $scope.bloodSugar.measureType = 'beforeBreakFast';
                            break;
                        case '早餐后':
                            $scope.bloodSugar.measureType = 'afterBreakFast';
                            break;
                        case '午餐前':
                            $scope.bloodSugar.measureType = 'beforeLunch';
                            break;
                        case '午餐后':
                            $scope.bloodSugar.measureType = 'afterLunch';
                            break;
                        case '晚餐前':
                            $scope.bloodSugar.measureType = 'beforeDinner';
                            break;
                        case '晚餐后':
                            $scope.bloodSugar.measureType = 'afterDinner';
                            break;
                        case '睡前':
                            $scope.bloodSugar.measureType = 'beforeSleep';
                            break;
                    }
                    var detectionData = [];
                    var bloodSugarData = {
                        measureTime:time,
                        mealType:$scope.bloodSugar.measureType,
                        bgValue:$scope.bloodSugar.measureNum,
                        remarks:$scope.bloodSugar.remark
                    };
                    detectionData.push(bloodSugarData);

                    if(submitOnOff) {
                        submitOnOff = false;

                        CreateDetection.save({
                            elderId: $scope.elderId,
                            elderName: $scope.elderName,
                            detectionType: 'bg',
                            detectionData: detectionData
                        }, function (data) {
                            $state.go('detectionDiagnose', {firstMenu: 'detection', secondMenu: 'bloodSugarTable'});
                        });
                    }


                    /*清除记录*/
                    $rootScope.measureBloodSugar = {};

                }
            }


            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            $scope.bloodSugar = {};

            /*只读*/
            if($stateParams.readOnly=='true')
            {
                $scope.bloodSugar.readOnly = true;
            }
            else if($stateParams.readOnly=='false')
            {
                $scope.bloodSugar.readOnly = false;
                $rootScope.measureBloodSugarRemark = '';
            }

            /*
             * 页面初始值
             * */
            if($rootScope.measureBloodSugar==null||angular.equals({}, $rootScope.measureBloodSugar)){
                $rootScope.measureBloodSugar = {};
                $scope.bloodSugar.measureNum = $stateParams.bloodSugarNum;
                $scope.bloodSugar.recorded = $stateParams.recorded;
                $scope.bloodSugar.remark = $rootScope.measureBloodSugarRemark;
                var time = $stateParams.timeDate.split(' ');
                $scope.bloodSugar.measureDate = time[0];
                $scope.bloodSugar.measureTime = time[1];
                switch($stateParams.timeType){
                    case 'dawn':
                        $scope.bloodSugar.measureType = '凌晨';
                        break;
                    case 'beforeBreakFast':
                        $scope.bloodSugar.measureType = '早餐前/空腹';
                        break;
                    case 'afterBreakFast':
                        $scope.bloodSugar.measureType = '早餐后';
                        break;
                    case 'beforeLunch':
                        $scope.bloodSugar.measureType = '午餐前';
                        break;
                    case 'afterLunch':
                        $scope.bloodSugar.measureType = '午餐后';
                        break;
                    case 'beforeDinner':
                        $scope.bloodSugar.measureType = '晚餐前';
                        break;
                    case 'afterDinner':
                        $scope.bloodSugar.measureType = '晚餐后';
                        break;
                    case 'beforeSleep':
                        $scope.bloodSugar.measureType = '睡前';
                        break;
                }
            }else{
                if($rootScope.measureBloodSugar.measureDate){
                    $scope.bloodSugar.measureTime = $rootScope.measureBloodSugar.measureTime;
                    $scope.bloodSugar.measureDate = $rootScope.measureBloodSugar.measureDate;
                }
                $scope.bloodSugar.measureNum = $rootScope.measureBloodSugar.measureNum;
                $scope.bloodSugar.measureType = $rootScope.measureBloodSugar.measureType;
                $scope.bloodSugar.recorded = $rootScope.measureBloodSugar.recorded;
            }


            /*默认时间显示*/
            $scope.bloodSugar.nowDate = $filter('date')(new Date().getTime(),'yyyy-MM-dd');
            $scope.bloodSugar.nowTime = $filter('date')(new Date().getTime(),'HH:mm');


            /*控制目标*/
            GetControlTarget.get({elderId:$scope.elderId,detectionType:'bg'},function(data){
                ElderUtil.checkResponseData(data);
                $scope.loadingStatus = false;
                if($stateParams.readOnly=="true")
                {
                    var data = data.responseData.detectionData[0];
                    if($scope.bloodSugar.measureType == '凌晨')
                    {
                        $scope.bloodSugar.objective = data.dawn;
                    }
                    else if($scope.bloodSugar.measureType == '早餐前/空腹')
                    {
                        $scope.bloodSugar.objective = data.fasting;
                    }
                    else if($scope.bloodSugar.measureType == '早餐后')
                    {
                        $scope.bloodSugar.objective = data.afterMeal;
                    }
                    else if($scope.bloodSugar.measureType == '午餐前')
                    {
                        $scope.bloodSugar.objective = data.beforeMeal;
                    }
                    else if($scope.bloodSugar.measureType == '午餐后')
                    {
                        $scope.bloodSugar.objective = data.afterMeal;
                    }
                    else if($scope.bloodSugar.measureType == '晚餐前')
                    {
                        $scope.bloodSugar.objective = data.beforeMeal;
                    }
                    else if($scope.bloodSugar.measureType == '晚餐后')
                    {
                        $scope.bloodSugar.objective = data.afterMeal;
                    }
                    else if($scope.bloodSugar.measureType == '睡前')
                    {
                        $scope.bloodSugar.objective = data.beforeBed;
                    }

                }
                else if($stateParams.readOnly=="false")
                {
                    var nowTime = new Date();
                    var hours = nowTime.getHours();
                    if(data.responseData.detectionData){
                        var data = data.responseData.detectionData[0];
                        /*默认控制目标显示*/
                        if(hours >= 0 && hours < 6){
                            $scope.bloodSugar.objective = data.dawn;
                            $scope.bloodSugar.measureType = '凌晨';
                        }else if(hours >= 6 && hours < 7){
                            $scope.bloodSugar.objective = data.fasting;
                            $scope.bloodSugar.measureType = '早餐前/空腹';
                        }else if(hours >= 7 && hours < 9 ) {
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = '早餐后';
                        }
                        else if(hours >= 9 && hours < 12 ){
                            $scope.bloodSugar.objective = data.beforeMeal;
                            $scope.bloodSugar.measureType = '午餐前';
                        }
                        else if(hours >= 12 && hours < 14 ){
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = '午餐后';
                        }
                        else if(hours >= 14 && hours < 18 ){
                            $scope.bloodSugar.objective = data.beforeMeal;
                            $scope.bloodSugar.measureType = '晚餐前';
                        }
                        else if(hours >= 18 && hours < 20 ){
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = '晚餐后';
                        }
                        else if(hours >= 20 && hours < 24){
                            $scope.bloodSugar.objective = data.beforeBed;
                            $scope.bloodSugar.measureType = '睡前';
                        }
                    }

                }
            });

            /*测量时间*/
            var currYear = (new Date()).getFullYear();
            var opt={};
            opt.datetime = {preset : 'datetime'};
            opt.default = {
                theme: 'ios', //皮肤样式
                display: 'bottom', //显示方式
                mode: 'scroller', //日期选择模式
                lang:'zh',
                dataFormat:'yyyy-mm-dd',
                startYear:currYear - 10, //开始年份
                endYear:currYear + 10, //结束年份
                setText:'保存',
                cancelText:'取消',
                headerText:'测量时间',
                onSet:mobiscroll_date_select
            };

            var optDateTime = $.extend(opt['datetime'], opt['default']);
            $("#measureTimeInp").mobiscroll(optDateTime).datetime(optDateTime);

            function mobiscroll_date_select(){
                var val = $('#measureTimeInp').val().replace(/\//g,'-');
                var attr = val.split(' ');
                $('#measureTime').html(attr[1]);
                $('#measureDate').html(attr[0]);
                $scope.bloodSugar = {};
                $rootScope.measureBloodSugar.measureTime = attr[1];
                $rootScope.measureBloodSugar.measureDate = attr[0];
                $scope.bloodSugar.measureTime = attr[1];
                $scope.bloodSugar.measureDate = attr[0];

                GetControlTarget.get({elderId:$scope.elderId,detectionType:'bg'},function(data){

                    $scope.loadingStatus = false;

                    var hours = $scope.bloodSugar.measureTime.substr(0,2);
                    if(data.responseData.detectionData){
                        var data = data.responseData.detectionData[0];
                        /*默认控制目标显示*/
                        if(hours >= 0 && hours < 6){
                            $scope.bloodSugar.objective = data.dawn;
                            $scope.bloodSugar.measureType = "凌晨";
                        }else if(hours >= 6 && hours < 7){
                            $scope.bloodSugar.objective = data.fasting;
                            $scope.bloodSugar.measureType = "早餐前/空腹";
                        }else if(hours >= 7 && hours < 9 ) {
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = "早餐后";
                        }
                        else if(hours >= 9 && hours < 12 ){
                            $scope.bloodSugar.objective = data.beforeMeal;
                            $scope.bloodSugar.measureType = "午餐前";
                        }
                        else if(hours >= 12 && hours < 14 ){
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = "午餐后";
                        }
                        else if(hours >= 14 && hours < 18 ){
                            $scope.bloodSugar.objective = data.beforeMeal;
                            $scope.bloodSugar.measureType = "晚餐前";
                        }
                        else if(hours >= 18 && hours < 20 ){
                            $scope.bloodSugar.objective = data.afterMeal;
                            $scope.bloodSugar.measureType = "晚餐后";
                        }
                        else if(hours >= 20 && hours < 24){
                            $scope.bloodSugar.objective = data.beforeBed;
                            $scope.bloodSugar.measureType = "睡前";
                        }
                    }
                });
            }

            /*测量值*/
            var firstBloodSugarNum = [];
            for(var i = 1; i < 33; i++){
                firstBloodSugarNum.push(i);
            }
            var secondBloodSugarNum = [];
            for(var i = 0; i < 10; i++){
                secondBloodSugarNum.push(i);
            }
            $('#measureCont').mobiscroll().scroller({
                theme: 'ios',
                lang:'zh',
                display: 'bottom',
                setText:'保存',
                cancelText:'取消',
                headerText:'测量值',
                wheels: [
                    [{
                        circular: false,
                        data: firstBloodSugarNum
                    }, {
                        circular: false,
                        data: secondBloodSugarNum
                    }]
                ],
                parseValue:function(){
                    return [4,0]
                },
                onSet:function(data){
                    var value = data.valueText.split(' ');
                    $('.measureNum').html(value[0] + '.' + value[1]);
                    $rootScope.measureBloodSugar.measureNum = value[0] + '.' + value[1];
                    $rootScope.measureBloodSugar.recorded = true;
                    $scope.bloodSugar.measureNum = value[0] + '.' + value[1];
                    $scope.bloodSugar.recorded = $rootScope.measureBloodSugar.recorded;
                    $scope.$apply();
                }
            });

        }]);
