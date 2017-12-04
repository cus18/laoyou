angular.module('controllers',[]).controller('groupChatCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetGroupChatData','$sce','$timeout',
        function ($scope,$rootScope,$stateParams,$state,GetGroupChatData,$sce,$timeout) {

            $scope.messageType = $stateParams.messageType;
            $scope.id = $stateParams.id;
            $scope.groupChatData = {};

            GetGroupChatData.get({messageType:$scope.messageType,
                id:$scope.id}, function(data){
                $scope.groupChatData = data.responseData;
                if($scope.messageType=="chatType7")
                {
                    $scope.groupChatData.treatmentAudio = $sce.trustAsResourceUrl($scope.groupChatData.treatmentAudio);
                }
            })

            /*音频播放*/
            $timeout(function(){
                var aud = document.getElementById('diagnoseAudio');
                $scope.audioCtrl = function(){
                    // console.log(aud.duration)
                    if(aud.paused){
                        aud.play();
                        $scope.paused = true;
                    }else{
                        aud.pause();
                        $scope.paused = false;
                    }
                }
            },1000);

        }])