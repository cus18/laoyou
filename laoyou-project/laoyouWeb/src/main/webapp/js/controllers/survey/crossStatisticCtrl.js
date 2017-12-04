/**
 * Created by 郑强丽 on 2017/9/15.
 */
angular.module('controllers',[]).controller('crossStatisticCtrl',
    ['$scope','$interval','$stateParams','$state','Global','$compile','CrossStatistic',
        function ($scope,$interval,$stateParams,$state,Global,$compile,CrossStatistic) {

            $scope.crossStatistic = {};
            $scope.charts = {};
            $scope.questionNames = [];

            $scope.independents = [
                {value:$scope.questionNames[0],isFirst:true}
            ];
            /*添加减少自变量*/
            $scope.addIndependent = function(){
                $scope.crossStatistic.selectHint = '';
                if($scope.independents.length < 2){
                    $scope.independents.push({value:$scope.questionNames[0],isFirst:false});
                }
                else
                {
                    $scope.crossStatistic.selectHint = '自变量的个数不能超过2个';
                }
            }
            $scope.reduceIndependent = function(index){
                $scope.independents.splice(index,1);
            }


            /*添加减少因变量*/
            $scope.dependents = [
                {value:$scope.questionNames[0],isFirst:true}
            ];
            $scope.addDependent = function(){
                $scope.crossStatistic.selectHint = '';
                if($scope.dependents.length < 10){
                    $scope.dependents.push({value:$scope.questionNames[0],isFirst:false});
                }
                else
                {
                    $scope.crossStatistic.selectHint = '因变量的个数不能超过10个';
                }
            }
            $scope.reduceDependent = function(index){
                $scope.dependents.splice(index,1);
            }

            //避免重复查询
            var onOff = true;
            $scope.questionChange = function(){
                onOff = true;
            }

            //点击查询
            $scope.analyze = function(){
                $scope.independentList = [];
                $scope.dependentList = [];
                $scope.questionIdList = [];
                $scope.crossStatistic.selectHint = '';

                if($scope.independents[0].value && $scope.dependents[0].value){

                    //获取自变量和因变量的值
                    angular.forEach($scope.independents,function(data){
                        $scope.independentList.push(data.value);
                        $scope.questionIdList.push(data.value.questionId);
                    })

                    angular.forEach($scope.dependents,function(data){
                        $scope.dependentList.push(data.value);
                        $scope.questionIdList.push(data.value.questionId)
                    })



                    //判断变量是否规范
                    var repeat = '';
                    $scope.questionIdList.sort();
                    for(var i = 0; i < $scope.questionIdList.length; i++){
                        if($scope.questionIdList[i] == $scope.questionIdList[i+1])
                        {
                            repeat = true;
                        }
                    }
                    if(repeat)
                    {
                        $scope.crossStatistic.selectHint = '自变量和因变量中不能有相同的因素';
                    }
                    else
                    {
                        if(onOff){
                            onOff = false;
                            $scope.loadShow = true;
                            CrossStatistic.save({
                                independentVariableList:$scope.independentList,
                                dependentVariableList:$scope.dependentList
                            },function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.response = data.responseData;

                                    //添加小计
                                    angular.forEach(data.responseData,function(data){
                                        angular.forEach(data,function(data){
                                            data.push(0);
                                            for(var i = 0; i < data.length-1; i++){
                                                data[data.length-1] += parseInt(data[i]);
                                            }
                                        })
                                    });

                                    //x轴变量
                                    $scope.independentX = [];
                                    if($scope.independentList[1]){
                                        for(var i = 0; i < $scope.independentList[0].questionData.length; i++){
                                            for(var j = 0; j < $scope.independentList[1].questionData.length; j++){
                                                $scope.independentX.push($scope.independentList[0].questionData[i].questionItemName + '/' + $scope.independentList[1].questionData[j].questionItemName);
                                            }
                                        }
                                    }else{
                                        for(var i = 0; i < $scope.independentList[0].questionData.length; i++){
                                            $scope.independentX.push($scope.independentList[0].questionData[i].questionItemName)
                                        }
                                    }
                                    //y轴变量
                                    $scope.independentY = [];
                                    $scope.indeY = [];              //保存y轴名称的临时变量
                                    angular.forEach($scope.dependentList,function(data){
                                        for(var i = 0; i < data.questionData.length; i++){
                                            $scope.indeY.push(data.questionData[i].questionItemName)
                                        }
                                        $scope.independentY.push($scope.indeY);
                                        $scope.indeY = [];          //每添加一次清除变量
                                    });

                                    //表格
                                    $scope.charts.title = {text: ''};
                                    $scope.tableLoad = function(index){
                                        $('.table').eq(index).toggle();
                                    }

                                    //堆叠条形图
                                    $scope.barLoad = function(index){
                                        $scope.ind = index;
                                        $scope.charts.series = [];          //charts图表数据
                                        $scope.seriesData = [];             //每一个因变量对应的y轴数据

                                        angular.forEach($scope.response,function(data){         //后台返回的数据处理
                                            //去掉小计
                                            for(var i = 0; i < $scope.independentX.length; i++){
                                                $scope.dataY = data[i].join(',').substring(0,data[i].join(',').lastIndexOf(',')).split(',');
                                                for(var j = 0; j < $scope.dataY.length; j++){
                                                    $scope.dataY[j] = parseInt($scope.dataY[j])
                                                }
                                                $scope.seriesData.push({                //添加每个表格因变量的数据到临时变量中
                                                    name:$scope.independentX[i],
                                                    data:$scope.dataY
                                                })
                                            }
                                            $scope.charts.series.push($scope.seriesData);
                                            $scope.seriesData = [];         //添加完一次后，清除临时变量seriesData的值
                                        });

                                        $scope.charts.options = [];
                                        angular.forEach($scope.independentY,function(data){
                                            $scope.charts.options.push(
                                                {
                                                    chart: {
                                                        type: 'bar'
                                                    },
                                                    xAxis: {
                                                        categories:data,                 //添加每个表格对应的y轴坐标
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
                                                        reversed:true
                                                    },
                                                    plotOptions: {
                                                        series:{
                                                            stacking:'normal'
                                                        }
                                                    },
                                                    credits: {		//去除右下角highcharts标志
                                                        enabled: false
                                                    },
                                                    exporting: {	//去除右上角导出按钮
                                                        enabled: false
                                                    }
                                                }
                                            )
                                        })
                                    }
                                    $scope.barLoad();
                                }
                                else
                                {
                                    alert(data.errorInfo);
                                }

                                $scope.loadShow = false;
                            })
                        }
                    }
                }
            }






        }])
