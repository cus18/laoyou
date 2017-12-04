angular.module('controllers',[]).controller('indexCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','MemberStatistics',
        'DoctorStatistics','NurseStatistics','DiabeticStatistics','HypertensiveStatistics',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,MemberStatistics,
                  DoctorStatistics,NurseStatistics,DiabeticStatistics,HypertensiveStatistics) {


            $scope.columnCharts = {};
            $scope.pieCharts = {};
            $scope.barCharts = {};

            //会员总人数
            $scope.memberSeries = [];
            MemberStatistics.get(function(data){
                if(data.responseData){
                    $scope.elderNum = data.responseData.elderNum;
                    angular.forEach(data.responseData,function(data,index,arrya){
                        if(index != 'elderNum'){
                            if(index == 'diabeticNum'){
                                index = '糖尿病患者'
                            }
                            if(index == 'hypertensiveNum'){
                                index = '高血压患者'
                            }
                            $scope.memberSeries.push({name:index,data:[data]});
                        }
                    })
                }
            })

            //医生总人数
            $scope.doctorSeries = [];
            DoctorStatistics.get(function(data){
                if(data.responseData){
                    $scope.doctorNum = data.responseData.total;
                    angular.forEach(data.responseData,function(data,index,array){
                        if(index != 'total'){
                            $scope.doctorSeries.push({data:[data],name:index});
                        }
                    })
                }
            })

            //护士总人数
            $scope.nurseSeries = [];
            NurseStatistics.get(function(data){
                if(data.responseData){
                    $scope.nurseNum = data.responseData.total;
                    angular.forEach(data.responseData,function(data,index,array){
                        if(index != 'total'){
                            $scope.nurseSeries.push({name:index,data:[data]});
                        }
                    })
                }
            })

            //糖尿病会员分析
            $scope.diabeticSeries = [{
                name:'糖尿病会员分析',
                data:[]
            }]
            DiabeticStatistics.get(function(data){
                if(data.responseData){
                    var total = data.responseData.diabeticNum;
                    var package = data.responseData.diabeticPackageNum;
                    $scope.diabeticSeries[0].data = [package,total-package]
                }
            })

            //高血压会员分析
            $scope.hypertensiveSeries = [{
                name:'高血压会员分析',
                data:[]
            }]
            HypertensiveStatistics.get(function(data){
                if(data.responseData){
                    var total = data.responseData.hypertensiveNum;
                    var package = data.responseData.hypertensivePackageNum;
                    $scope.hypertensiveSeries[0].data = [package,total-package]
                }
            })



            $scope.columnCharts.options = {
                title:'',
                chart: {
                    type: 'column'
                },
                xAxis: {
                    categories:''
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                plotOptions: {
                    column: {
                        dataLabels: {
                            enabled: true,
                            allowOverlap: true
                        }
                    }
                },
                legend: {
                    enabled: true			//隐藏data中的name显示
                },
                credits: {		            //去除右下角highcharts标志
                    enabled: false
                }
            };

            $scope.pieCharts.options = {
                title:'',
                chart: {
                   type:'pie'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true
                    }
                },
                legend: {
                    enabled: false			//隐藏data中的name显示
                },
                credits: {		            //去除右下角highcharts标志
                    enabled: false
                }
            };
            $scope.barCharts.options = {
                title:'',
                chart: {
                    type: 'bar'
                },
                xAxis: {
                    categories: ['已签约服务套餐','未签约服务套餐'],
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                plotOptions: {
                    bar: {
                        dataLabels: {
                            enabled: true,
                            allowOverlap: true
                        }
                    }
                },
                legend: {
                    enabled: false			//隐藏data中的name显示
                },
                credits: {		            //去除右下角highcharts标志
                    enabled: false
                }
            };

        }])