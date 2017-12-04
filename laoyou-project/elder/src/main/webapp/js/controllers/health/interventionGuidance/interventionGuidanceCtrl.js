angular.module('controllers',['ui.calendar','ui.bootstrap']).controller('interventionGuidanceCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetMedicationPlan','GetDietPlanByDate','GetDetectionHealthData',
        'ElderUtil','$filter','$timeout','GetMedicationPlanTimingByElderUserID','Global','$window',
        function($scope,$rootScope,$stateParams,$state,GetMedicationPlan,GetDietPlanByDate,GetDetectionHealthData,
                 ElderUtil,$filter,$timeout,GetMedicationPlanTimingByElderUserID,Global,$window){

            $scope.loadingStatus = true;

            var loadMealRecord = function(){
                $timeout(function() {
                    $scope.mealRecordUiConfig = {
                        calendar:{
                            height: 400,
                            editable: false,
                            header:{
                                left: '',
                                center: 'prev title next',
                                right: ''
                            },
                            events:function(start,end,timezone,callback){
                                var startDate = $filter('date')(new Date(start).getTime(),'yyyy-MM-dd');
                                var endDate = $filter('date')(new Date(end).getTime(),'yyyy-MM-dd');

                                if(!$scope.secondMenu=="")
                                {
                                    startDate = $scope.secondMenu;
                                    endDate = $scope.secondMenu;
                                }

                                $scope.loadingStatus = true;
                                GetDietPlanByDate.save({elderUserID:$scope.elderId,startDate:startDate,endDate:endDate},function(data){
                                    ElderUtil.checkResponseData(data);
                                    $scope.loadingStatus = false;
                                    $scope.mealRecordDatas = data.responseData;
                                    $scope.mealRecordEvents = [];

                                    angular.forEach(data.responseData, function (value, index, array) {
                                        var year = $filter('date')(new Date(value.createDate).getTime(), 'yyyy');
                                        var month = $filter('date')(new Date(value.createDate).getTime(), 'M');
                                        var day = $filter('date')(new Date(value.createDate).getTime(), 'd');
                                        var date = new Date(year, month - 1, day);
                                        var reportEvent = {
                                            title: '饮食',
                                            allDay: true,
                                            start: date,
                                            url: 'elder#/interventionGuidance/mealRecord,' + value.createDate + ','
                                        }
                                        if (JSON.stringify($scope.mealRecordEvents).indexOf($filter('date')(new Date(value.createDate).getTime(), 'yyyy-MM-dd')) == -1) {
                                            $scope.mealRecordEvents.push(reportEvent);
                                        };
                                        callback($scope.mealRecordEvents);
                                    });
                                });
                            }
                        }
                    };
                }, 1000);
            }

            $scope.createMealRecord = function(){
                window.WebViewJavascriptBridge.callHandler(
                    'createMealRecord',
                    function(responseData) {
                    }
                );

                connectWebViewJavascriptBridge(function(bridge) {

                    bridge.registerHandler("createMealRecordDown", function(data, responseCallback) {
                        if(data == Global.SUCCESS)
                        {
                            $window.location.reload();
                        }
                        responseCallback(responseData);
                    });
                });
            }

            $scope.goMenu = function(firstMenuParam,secondMenuParam){
                $state.go('interventionGuidance',{firstMenu:firstMenuParam,secondMenu:secondMenuParam})
            };
            $scope.goMedicationPlan = function(pageType,editable,listId){
                $state.go('medicationPlan',{pageType:pageType,editable:editable,listId:listId})
            };
            $scope.goMealRecord = function(date,time){
                $state.go('mealRecordResult',{date:date,time:time})
            };

            $scope.firstMenu = $stateParams.firstMenu;
            $scope.secondMenu = $stateParams.secondMenu;
            $scope.recordDate = $stateParams.recordDate;
            $rootScope.h5Page = true;

            var initSportDate = function(num){
                var date = new Date();
                for(var i=0; i<num; i++)
                {
                    var newDate = ElderUtil.getAddDate(date.getTime(),0-i);
                    $scope.sportDate.push(newDate);
                    $scope.sportDateX.push(newDate.substring(5,10));
                }
            };

            $scope.elderId = $rootScope.rootElderId;
            $scope.elderName = $rootScope.rootElderName;

            if($scope.firstMenu=="medicineIntervention")
            {
                if($scope.secondMenu=="interventionPlan")
                {
                    $scope.loadingStatus = true;
                    GetMedicationPlan.get({elderUserID:$scope.elderId},function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.loadingStatus = false;
                        $scope.uncompletedMedicationPlan = data.responseData.uncompleted;
                        $scope.completeMedicationPlan = data.responseData.complete;
                    });
                }
                else if($scope.secondMenu=="medicationRecord")
                {
                    /*服药记录*/
                    $timeout(function(){
                        $scope.medicationRecordUiConfig = {
                            calendar:{
                                height: 400,
                                editable: false,
                                header:{
                                    left: '',
                                    center: 'prev title next',
                                    right: ''
                                },
                                events:function(start,end,timezone,callback){
                                    var startDate = $filter('date')(new Date(start).getTime(),'yyyy-MM-dd');
                                    var endDate = $filter('date')(new Date(end).getTime(),'yyyy-MM-dd');

                                    if(!$scope.recordDate=="")
                                    {
                                        startDate = $scope.recordDate;
                                        endDate = $scope.recordDate;
                                    }
                                    $scope.loadingStatus = true;
                                    GetMedicationPlanTimingByElderUserID.save({elderID:$scope.elderId,startTime:startDate,endTime:endDate},function(data){
                                        ElderUtil.checkResponseData(data);
                                        $scope.loadingStatus = false;
                                        $scope.medicationRecordDatas = data.responseData;
                                        $scope.medicationRecordEvents = [];

                                        angular.forEach(data.responseData, function (value, index, array) {

                                            if(value.status == '1'){
                                                value.status = '已服用';
                                            }
                                            else if(value.status == '0' || value.status == '')
                                            {
                                                value.status = '未服用';
                                            }

                                            var year = $filter('date')(new Date(value.usageDate).getTime(), 'yyyy');
                                            var month = $filter('date')(new Date(value.usageDate).getTime(), 'M');
                                            var day = $filter('date')(new Date(value.usageDate).getTime(), 'd');
                                            var date = new Date(year, month - 1, day);
                                            var reportEvent = {
                                                title: '服药',
                                                allDay: true,
                                                start: date,
                                                url: 'elder#/interventionGuidance/medicineIntervention,medicationRecord,' + value.usageDate
                                            }
                                            if (JSON.stringify($scope.medicationRecordEvents).indexOf($filter('date')(new Date(value.usageDate).getTime(), 'yyyy-MM-dd')) == -1) {
                                                $scope.medicationRecordEvents.push(reportEvent);
                                            };
                                            callback($scope.medicationRecordEvents);
                                        });
                                    });
                                }
                            }
                        };
                    },100)
                }

            }
            else if($scope.firstMenu=="mealRecord")
            {
                /*饮食记录*/
                loadMealRecord();

            }
            else if($scope.firstMenu=="sportRecord")
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
                        type: 'column',
                        backgroundColor:'#f8f8f8'
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

                if($scope.secondMenu =='week'){
                    initSportDate(7);
                }

                $scope.loadingStatus = true;
                GetDetectionHealthData.get({detectionType:'pdr',
                    detectionDateType:$scope.secondMenu,elderId:$scope.elderId},function(data) {

                    $scope.loadingStatus = false;
                    ElderUtil.checkResponseData(data);

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

        }]);

