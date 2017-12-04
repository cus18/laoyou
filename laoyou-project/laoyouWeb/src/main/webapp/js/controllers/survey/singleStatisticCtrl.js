/**
 * Created by 郑强丽 on 2017/9/14.
 */
angular.module('controllers',[]).controller('singleStatisticCtrl',
    ['$scope','$interval','$stateParams','$filter','$state','Global','SingleStatistic',
        function ($scope,$interval,$stateParams,$filter,$state,Global,SingleStatistic) {

            $scope.charts = {};
            $scope.questionNames = [];
            $scope.singleStatistic = {};

            $scope.charts.title = {text: '丰台区老年人情况调查'};
            $scope.charts.series = [{name:$scope.questionName,colorByPoint:true,data:[]}];

            //查询
            $scope.questionNameChange = function(){
                $scope.loadShow = true;
                SingleStatistic.save($scope.question,function(data){
                    if(data.result == Global.SUCCESS){
                        $scope.singleStatistic.response = data.responseData;
                        var totalNum = 0;
                        angular.forEach(data.responseData,function(data){
                            data.singleStatisticNum = parseInt(data.singleStatisticNum);
                            data.y = data.singleStatisticNum;
                            data.name = data.singleStatisticName;
                            totalNum  += data.singleStatisticNum;
                        });
                        $scope.singleStatistic.totalNum = totalNum;
                        $scope.charts.series[0].data = data.responseData;
                    }
                    else
                    {
                        alert(data.errorInfo)
                    }
                    $scope.loadShow = false;
                });
            }

            //表格
            $scope.tableLoad = function(){
                $scope.tableHide = !$scope.tableHide;
            }
            //饼图
            $scope.pieLoad = function(){
                $scope.charts.hCharts_show = true;
                $scope.charts.options =  {
                    chart: {
                        type: 'pie'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        title: {
                            text: ''
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                allowOverlap:true,
                                formatter: function() {
                                    if (this.percentage > 0){
                                        return '<b>' + this.point.singleStatisticName + '</b>:<br/> ' + Highcharts.numberFormat(this.y, 0, ',') + '人<br/> ' +
                                            Highcharts.numberFormat(this.percentage, 1) + ' %';
                                    }
                                },
                                style:{
                                    fontSize:'16px'
                                }
                            }
                        }
                    },
                    tooltip: {
                        formatter: function() {
                            return '<b>' + this.point.singleStatisticName + '</b>: ' + Highcharts.numberFormat(this.percentage, 1) + '% (' +
                                Highcharts.numberFormat(this.y, 0, ',') + ' 人)';
                        }
                    },
                    // colors:[
                    //     '#4dd3b9','#fdd67f','#ffaca8','#64bcec'
                    // ],
                    credits: {		//去除右下角highcharts标志
                        enabled: false
                    },
                    exporting: {	//去除右上角导出按钮
                        enabled: false
                    }
                };

            }
            //条形图
            $scope.barLoad = function(){
                $scope.charts.hCharts_show = true;
                $scope.charts.options =  {
                    chart: {
                        type: 'bar'
                    },
                    xAxis: {
                        type: 'category',
                        labels:{
                            style:{
                                fontSize:'16px'
                            }
                        }
                    },
                    yAxis: {
                        title: {
                            text: ''
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        series: {
                            borderWidth: 0,
                            dataLabels: {
                                enabled: true,
                                format: '{point.y}',
                                style:{
                                    fontSize:'16px',
                                    fontWeight:'100'
                                }
                            }
                        }
                    },
                    tooltip: {
                        formatter: function() {
                            return '<b>' + this.point.singleStatisticName + '</b>: '+ Highcharts.numberFormat(this.y, 0, ',') + ' 人';
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

        }])
