/**
 * Created by 郑强丽 on 2017/5/20.
 */
angular.module('controllers',[]).controller('bloodPressureRecordCtrl',
    ['$scope','$timeout','$rootScope','$stateParams','$filter','$state','CreateDetection','GetControlTarget','ElderUtil',
        function ($scope,$timeout,$rootScope,$stateParams,$filter,$state,CreateDetection,GetControlTarget,ElderUtil) {

            $scope.loadingStatus = true;

            $scope.goMenu = function(firstMenuParam,secondMenuParam){
                $state.go("detectionDiagnose", {firstMenu: firstMenuParam, secondMenu: secondMenuParam});
            };

            $scope.enterGroupTalk = function(){
                window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
            }

            /*提交*/
            var submitOnOff = true;        //防止多次提交
            $scope.submitForm = function(){
                if($scope.bloodPressure.diastolic){

                    if($scope.bloodPressure.measureTime){
                        var time = $scope.bloodPressure.measureDate + ' ' + $scope.bloodPressure.measureTime;
                    }else{
                        var time = $scope.bloodPressure.nowDate + ' ' + $scope.bloodPressure.nowTime;
                    }

                    var detectionData = [];
                    var bloodPressureData = {
                        systolic:$scope.bloodPressure.systolic,
                        diastolic:$scope.bloodPressure.diastolic,
                        heartRate:$scope.bloodPressure.heartRate,
                        measureTime:time,
                        remarks:$scope.bloodPressure.remark
                    };
                    detectionData.push(bloodPressureData);

                    if(submitOnOff){
                        submitOnOff = false;

                        CreateDetection.save({
                            elderId:$scope.elderId,
                            elderName:$scope.elderName,
                            detectionType:'bp',
                            detectionData:detectionData
                        },function(data){
                            ElderUtil.checkResponseData(data);
                            $state.go('detectionDiagnose',{firstMenu:'detection',secondMenu:'bloodPressureTable'});
                        });
                    }



                    /*清除记录*/
                    $rootScope.measureBloodPressure = {};
                }
            }


            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            $scope.bloodPressure = {};

            /*只读*/
            if($stateParams.readOnly=='true')
            {
                $scope.bloodPressure.readOnly = true;
            }
            else if($stateParams.readOnly=='false')
            {
                $scope.bloodPressure.readOnly = false;
                $rootScope.measureBloodPressureRemark = '';
            }

            /*
             * 页面初始值
             * */
            if($rootScope.measureBloodPressure==null||angular.equals({}, $rootScope.measureBloodPressure)){
                $rootScope.measureBloodPressure = {};
                $scope.bloodPressure.diastolic = $stateParams.diastolic;
                $scope.bloodPressure.systolic = $stateParams.systolic;
                $scope.bloodPressure.heartRate = $stateParams.heartRate;
                $scope.bloodPressure.emptyCont = $stateParams.emptyCont;
                $scope.bloodPressure.remark = $rootScope.measureBloodPressureRemark;
                var time = $stateParams.measureTime.split(' ');
                $scope.bloodPressure.measureDate = time[0];
                $scope.bloodPressure.measureTime = time[1];
            }else{
                if($rootScope.measureBloodPressure.measureDate){
                    $scope.bloodPressure.measureTime = $rootScope.measureBloodPressure.measureTime;
                    $scope.bloodPressure.measureDate = $rootScope.measureBloodPressure.measureDate;
                }
                $scope.bloodPressure.diastolic = $rootScope.measureBloodPressure.diastolic;
                $scope.bloodPressure.systolic = $rootScope.measureBloodPressure.systolic;
                $scope.bloodPressure.heartRate = $rootScope.measureBloodPressure.heartRate;
                $scope.bloodPressure.emptyCont = $rootScope.measureBloodPressure.emptyCont;
            }

            $scope.settings = {
                theme: "android-holo-light",
                min: new Date(2014, 8, 15),
                max: new Date(2024, 8, 14)
            };

            /*默认时间显示*/
            $scope.bloodPressure.nowDate = $filter('date')(new Date().getTime(),'yyyy-MM-dd');
            $scope.bloodPressure.nowTime = $filter('date')(new Date().getTime(),'HH:mm');

            /*控制目标*/
            GetControlTarget.get({elderId:$scope.elderId,detectionType:'bp'},function(data){
                ElderUtil.checkResponseData(data);
                $scope.loadingStatus = false;
                if(data.responseData.detectionData){
                    var data = data.responseData.detectionData[0];
                    /*默认控制目标显示*/
                    $scope.bloodPressure.objective = data.diastolic + ' ' + data.systolic + ' ' + data.heartRate;
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
                $rootScope.measureBloodPressure.measureTime = attr[1];
                $rootScope.measureBloodPressure.measureDate = attr[0];
                $scope.bloodPressure.measureTime = attr[1];
                $scope.bloodPressure.measureDate = attr[0];
            }



            /*测量值*/
            var diastolicNum = ['<90'],
                systolicNum = ['<60'],
                heartRateNum = ['<60'];
            for(var i = 90; i < 141; i++){
                diastolicNum.push(i);
            }
            diastolicNum.push('>140');

            for(var i = 60; i < 91; i++){
                systolicNum.push(i);
            }
            systolicNum.push('>90');


            for(var i = 60; i < 101; i++){
                heartRateNum.push(i);
            }
            heartRateNum.push('>100');

            $('#measureCont').mobiscroll().scroller({
                theme: 'ios',
                lang:'zh',
                display: 'bottom',
                setText:'保存',
                cancelText:'取消',
                headerText:'高压/低压/心率',
                wheels: [
                    [{
                        circular: false,
                        data: diastolicNum
                    }, {
                        circular: false,
                        data: systolicNum
                    }, {
                        circular: false,
                        data: heartRateNum
                    }]
                ],
                parseValue:function(){
                    return [120,80,75]
                },
                onSet:function(data){
                    var value = data.valueText.split(' ');
                    $rootScope.measureBloodPressure.diastolic = value[0];
                    $rootScope.measureBloodPressure.systolic = value[1];
                    $rootScope.measureBloodPressure.heartRate = value[2];
                    $rootScope.measureBloodPressure.emptyCont = true;
                    $scope.bloodPressure.diastolic = value[0];
                    $scope.bloodPressure.systolic = value[1];
                    $scope.bloodPressure.heartRate = value[2];
                    $scope.bloodPressure.emptyCont = true;
                    $scope.$apply();
                }
            });
}])
