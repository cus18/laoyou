angular.module('controllers',[]).controller('groupChatCtrl',
    ['$scope','$rootScope','$stateParams','$state','GetGroupChatData','$sce',
        function ($scope,$rootScope,$stateParams,$state,GetGroupChatData,$sce) {

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

        }])