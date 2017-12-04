angular.module('controllers',[]).controller('courseDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','GetLiveBroadCastDetail',
        'GetOnlineCourseDetail','GetOnlineCourseDiscuss','$sce','CreateOnlineCourseDiscuss','ElderUtil',
        function ($scope,$interval,$rootScope,$stateParams,$state,GetLiveBroadCastDetail,
                  GetOnlineCourseDetail,GetOnlineCourseDiscuss,$sce,CreateOnlineCourseDiscuss,ElderUtil) {

            $scope.param = {
                courseId:$stateParams.courseId,
                startCourseFlag : false,
                discussPage:{
                    pageNo:1,
                    pageSize:2,
                    orderType:1
                },
                submitDiscussContent : ""

            }

            $scope.startCourse = function(courseId){
                $scope.param.startCourseFlag = true;
                $scope.param.discussPage.pageSize = 10;
                $scope.param.playIndex = 0;
                $scope.param.playURL = angular.copy($sce.trustAsResourceUrl($scope.param.onlineCourseDetail.onLineCourseDataDTOList[0].onlineCourseURL));

            }

            if($stateParams.courseType=="vod")
            {
                GetOnlineCourseDetail.save({onlineCourseId:$scope.param.courseId},function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.param.onlineCourseDetail = data.responseData;
                    $scope.param.discussPage.requestData = $scope.param.onlineCourseDetail.onlineCourseId;
                    GetOnlineCourseDiscuss.save($scope.param.discussPage,function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.onlineCourseDiscussData  = data.responseData;
                    })
                })
            }

            $scope.loadMoreDiscuss = function(){
                $scope.param.discussPage.pageSize = $scope.param.discussPage.pageSize + 2;
                GetOnlineCourseDiscuss.save($scope.param.discussPage,function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.onlineCourseDiscussData  = data.responseData;
                })
            }

            $scope.playCourse = function(newIndex){

                $scope.param.playIndex = newIndex;
                angular.forEach($scope.param.onlineCourseDetail.onLineCourseDataDTOList,
                    function(value,index,array){

                    if(newIndex==index)
                    {
                        $scope.param.playURL = angular.copy($sce.trustAsResourceUrl($scope.param.onlineCourseDetail.onLineCourseDataDTOList[index].onlineCourseURL));
                    }
                })
            }

            $scope.submitDiscuss = function(){
                CreateOnlineCourseDiscuss.save({onlineCourseId:$scope.param.onlineCourseDetail.onlineCourseId,
                    onlineCourseDiscussContent:$scope.param.submitDiscussContent},function(data){
                    ElderUtil.checkResponseData(data);
                    $scope.onlineCourseDiscussData.onlineCourseDiscussDTOList = data.responseData;
                    $scope.param.submitDiscussContent = "";
                    GetOnlineCourseDiscuss.save($scope.param.discussPage,function(data){
                        ElderUtil.checkResponseData(data);
                        $scope.onlineCourseDiscussData  = data.responseData;
                    })
                })
            }

            $scope.return = function(){
                connectWebViewJavascriptBridge(function() {
                    window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
            }

        }])