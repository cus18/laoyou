/**
 * Created by 郑强丽 on 2017/9/15.
 */
angular.module('controllers',[]).controller('diyStatisticCtrl',
    ['$scope','$interval','$stateParams','$state','Global','$compile','DiyStatistic',
        function ($scope,$interval,$stateParams,$state,Global,$compile,DiyStatistic) {


            $scope.questionNames = [];
            $scope.symbols = [
                {name:'等于',value:'equal'},
                {name:'不等于',value:'notEqual'}
            ];
            $scope.symbols_age = [
                {name:'等于',value:'equal'},
                {name:'不等于',value:'notEqual'},
                {name:'大于',value:'larger'},
                {name:'小于',value:'smaller'}
            ];
            $scope.symbols_inp = [
                {name:'等于',value:'inputEqual'},
                {name:'不等于',value:'inputNotEqual'}
            ];


            $scope.questionList = [
                {surveyDTO:$scope.questionNames[0],isFirst:true}
            ];

            /*添加查询条件*/
            $scope.addCondition = function(){
                $scope.selectHint = '';
                $scope.questionList.push({surveyDTO:$scope.questionNames[0],isFirst:false});

            }
            $scope.reduceCondition = function(index){
                $scope.questionList.splice(index,1);
            }

            //避免重复查询
            var onoff = true;
            $scope.conditionChange = function(){
                onoff = true;
                $scope.selectHint = '';
            }

            //查询
            $scope.demand = function(){
                // angular.forEach($scope.questionList,function(data){
                //     delete data.isFirst;
                // })
                console.log($scope.questionList)
                if($scope.questionList[0].statisticValue && $scope.questionList[0].condition){
                    if(onoff){
                        onoff = false;
                        $scope.loadShow = true;
                        DiyStatistic.save($scope.questionList,function(data){
                            if(data.result == Global.SUCCESS){
                                $scope.resultNum = data.responseData[0].diyStatisticResponseValue;
                                $scope.totalNum = data.responseData[1].diyStatisticResponseValue;
                            }
                            else
                            {
                                alert(data.errorInfo);
                            }
                            $scope.loadShow = false;
                        })
                    }
                }
                else{
                    $scope.selectHint = '查询条件不能为空';
                }
            }




        }])

